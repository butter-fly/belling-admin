package com.belling.admin.service.impl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belling.admin.mapper.LoginLogMapper;
import com.belling.admin.mapper.UserMapper;
import com.belling.admin.model.LoginLog;
import com.belling.admin.model.User;
import com.belling.admin.service.SettingsService;
import com.belling.admin.service.UserOnlineService;
import com.belling.admin.service.UserService;
import com.belling.base.contants.ResponseCode;
import com.belling.base.model.Pagination;
import com.belling.base.model.ResponseResult;
import com.belling.base.provider.PasswordProvider;
import com.belling.base.service.impl.BaseServiceImpl;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月22日 下午9:40:54  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, Integer> implements UserService {
	
	/**
	 * 登录日志Mapper对象
	 */
	@Autowired
	private LoginLogMapper loginLogMapper;
	
	/**
	 * 设置业务对象
	 */
	@Autowired
	private SettingsService settingsService;
	
	/**
	 * Shiro用户会话对象
	 */
	@Autowired
	private UserOnlineService userOnlineService;
	
	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#setMapper(com.belling.base.mapper.BaseMapper)
	 */
	@Autowired
	public void setMapper(UserMapper mapper) {
		this.mapper = mapper;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#enable(java.lang.Boolean, java.util.List)
	 */
	@Override
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(mapper.enable(isEnable, idList), idList.size(), "管理员数据库更新失败");
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#resetPassword(java.lang.String, java.util.List)
	 */
	@Override
	public void resetPassword(String password, List<Integer> idList) {
		verifyRows(mapper.resetPassword(password, idList), idList.size(), "管理员密码数据库重置失败");
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#findPaginationByAccount(java.lang.String, java.lang.Integer, com.belling.base.model.Pagination)
	 */
	@Override
	public Pagination<User> findPaginationByAccount(String account, Integer appId, Pagination<User> p) {
		mapper.findPaginationByAccount(account, appId, p);
		return p;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#findByAccount(java.lang.String)
	 */
	@Override
	public User findByAccount(String account) {
		return mapper.findByAccount(account);
	}
	
	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#updatePassword(java.lang.Integer, java.lang.String)
	 */
	@Override
	@Transactional
	public void updatePassword(Integer id, String newPassword) {
		User user = get(id);
		user.setPassword(PasswordProvider.encrypt(newPassword));
		update(user);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#selectRolesPowers(java.lang.String)
	 */
	@Override
	public Map<String, Collection<String>> selectRolesPowers(Integer userId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#login(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	@Override
	public ResponseResult login(String ip, String account, String password, Boolean rememberMe) {
		ResponseResult result = ResponseResult.createSuccessResult();
		User user = findByAccount(account);
		if (user == null) {
			result.setCode(ResponseCode.ERROR).setMessage("登录名不存在");
		} else if (!user.getPassword().equals(password)) {
			result.setCode(ResponseCode.ERROR).setMessage("密码不正确");
		} else if (!user.getIsEnable()) {
			result.setCode(ResponseCode.ERROR).setMessage("已被管理员禁用");
		} else {
			// 是否允许多地登录
			String multLogin = settingsService.getValueByKey("mult_login");
			
			// 判断是否已登录
			boolean rs = userOnlineService.hasLogin(account);
			// 不允许
			if ("0".equals(multLogin)) {
				// 是否允许多地登录
				String killLogin = settingsService.getValueByKey("kill_login");
				// 不允许挤掉之前用户登录
				if ("0".equals(killLogin)) {
					// 判断是否已经登录
					if (rs) {
						result.setCode(ResponseCode.ERROR).setMessage("账号已在另外一处地点登录，请先下线再登录");
						return result;
					}
				} 
				// 踢出之前登录用户
				if (rs) {
					userOnlineService.kickoutByAccount(account);
				}
			} 
			
			// 登录
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), password);
			currentUser.login(token);// 登录认证 记录登陆信息
			token.setRememberMe(rememberMe);
			System.out.println("****登陆成功*****");
			
			user.setLastLoginIp(ip);
			user.setLoginCount(user.getLoginCount() + 1);
			user.setLastLoginTime(new Date());
			mapper.update(user);
			
			
			// 记录日志
			LoginLog log = new LoginLog();
			log.setUserId(user.getAccount());
			log.setLoginType((short) 1);
			log.setLoginDesc("登录成功");
			log.setIpInfoCountry(null);
			log.setIpInfoRegion(null);
			log.setIpInfoCity(null);
			log.setIpInfoIsp(null);
			log.setLoginIp(ip);
			log.setLoginTime(new Timestamp(new Date().getTime()));
			
			// 保存登录日志
			loginLogMapper.insert(log);
			
			Session session = currentUser.getSession();
			session.setAttribute("user", user);
			session.setAttribute("userid", user.getAccount());
			// session.setAttribute("kickout", false); // 标记是否下线  
		}
		return result;
	}
}
