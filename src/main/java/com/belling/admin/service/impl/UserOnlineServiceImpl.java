package com.belling.admin.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belling.admin.dto.UserOnlineDTO;
import com.belling.admin.mapper.LoginLogMapper;
import com.belling.admin.model.LoginLog;
import com.belling.admin.service.UserOnlineService;
import com.belling.base.model.Pagination;
import com.belling.base.util.RequestUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		杭州启冠网络技术有限公司
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月29日 上午11:54:51  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Service("userOnlineService")
public class UserOnlineServiceImpl implements UserOnlineService {
	
	/**
	 * Shiro用户会话对象
	 */
	@Autowired
	private SessionDAO sessionDAO;
	
	/**
	 * 用户会话管理对象
	 */
	@Autowired
	private  SessionManager sessionManager;
	
	/**
	 * 登录日志Mapper对象
	 */
	@Autowired
	private LoginLogMapper loginLogMapper;

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserOnlineService#findPagination(com.belling.base.model.Pagination)
	 */
	@Override
	public Pagination<UserOnlineDTO> findPagination(Pagination<UserOnlineDTO> p) {
		List<UserOnlineDTO> onlineUserList = Lists.newArrayList();
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		Iterator<Session> it = sessions.iterator();
		// 遍历session
		while (it.hasNext()) {
			Session session = it.next();
			//标记为已提出的不加入在线列表
			if(session.getAttribute("kickout") == null ? false : true) continue;
			UserOnlineDTO onlineUser = getSessionDTO(session);
			if(onlineUser != null){
				onlineUserList.add(onlineUser);
			}
		}
		// 再将List<UserOnlineDTO>转换成page对象
		int page = p.getPageNo();
		int pageStartRow, pageEndRow;
		int totalRows = onlineUserList.size();
		p.setRowCount(totalRows);
		// 判断是否为最后一页  
		if (page * p.getPageSize() < totalRows) {
			pageEndRow = page * p.getPageSize();
			pageStartRow = pageEndRow - p.getPageSize();
		} else {
			pageEndRow = totalRows;
			pageStartRow = p.getPageSize() * (new Long(p.getPageCount()).intValue() - 1); 
		}
		
		if (totalRows > 0) {
			p.setList(onlineUserList.subList(pageStartRow, pageEndRow));
		} else {
			p.setList(new ArrayList<UserOnlineDTO>());
		}
		return p;
	}
	
	/**
	 * 从session中获取UserOnline对象
	 * 
	 * @param session
	 * @return
	 */
	private UserOnlineDTO getSessionDTO(Session session){
		if (null == session) {
			return null;
		}
		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		if (null == obj) {
			return null;
		}
		//存储session
		UserOnlineDTO userDto = new UserOnlineDTO();
		// 登录账号
		userDto.setLoginAccount(obj.toString());
		//最后一次和系统交互的时间
		userDto.setLastAccess(session.getLastAccessTime());
		// 开始时间
		userDto.setStartTime(session.getStartTimestamp());
		//主机的ip地址
		userDto.setIp(session.getHost());
		//session ID
		userDto.setSessionId(session.getId().toString());
		//回话到期 ttl(ms)
		userDto.setTimeout(session.getTimeout());
		//session创建时间
		userDto.setStartTime(session.getStartTimestamp());
		return userDto;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserOnlineService#kickout(java.lang.String)
	 */
	@Override
	public void kickoutByAccount(String account) {
		if (Strings.isNullOrEmpty(account)) return;
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		if (sessions.size() <= 0) return;
		System.out.println("kickoutByAccount sessions size is :" + sessions.size());
		for(Session session : sessions){
			Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if (obj != null) {
				String tempName = obj.toString();
				if (account.equals(tempName)) {
					// 会话已失效  但在线列表仍可获取Session会话对象
					session.setAttribute("kickout", true); // 标记为已下线
					session.setTimeout(0L); //设置session立即失效，即将其踢出系统break;
					// session.stop(); //销毁Shiro的会话
					
					// 记录日志
					LoginLog log = new LoginLog();
					log.setUserId(account);
					log.setLoginType((short) 1);
					log.setLoginDesc("账号异地登录，被迫强制下线");
					log.setIpInfoCountry(null);
					log.setIpInfoRegion(null);
					log.setIpInfoCity(null);
					log.setIpInfoIsp(null);
					log.setLoginIp(RequestUtil.getAddr(RequestUtil.getRequest()));
					log.setLoginTime(new Timestamp(new Date().getTime()));
					
					// 保存退出日志
					loginLogMapper.insert(log);
					break;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserOnlineService#kickoutBySessionId(java.lang.String)
	 */
	@Override
	public void kickoutBySessionId(String sessionId) {
		Session session = getSessionBysessionId(sessionId);
		if (null != session) {
			session.setAttribute("kickout", true); // 标记为已下线
			session.setTimeout(0L); //设置session立即失效，即将其踢出系统break;
			// session.stop(); //销毁Shiro的会话
		}
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserOnlineService#getSessionBysessionId(java.io.Serializable)
	 */
	@Override
	public Session getSessionBysessionId(Serializable sessionId) {
		Session session = sessionManager.getSession(new DefaultSessionKey(sessionId));
		return session;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserOnlineService#hasLogin(java.lang.String)
	 */
	@Override
	public boolean hasLogin(String account) {
		try {
			if (Strings.isNullOrEmpty(account)) return true;
			Collection<Session> sessions = sessionDAO.getActiveSessions();
			for(Session session : sessions){
				Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
				if (null != obj) {
					if (account.equals(obj.toString()) && session.getAttribute("kickout") == null) {
						return true;
					}
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
