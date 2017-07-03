package com.belling.admin.service;

import java.io.Serializable;

import org.apache.shiro.session.Session;

import com.belling.admin.dto.UserOnlineDTO;
import com.belling.base.model.Pagination;

/**  
 * <pre>
 * Description	
 * Copyright:	Copyright (c)2017
 * Company:		杭州启冠网络技术有限公司
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月29日 上午11:52:31  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
public interface UserOnlineService {
	
	/**
	 * 根据登录名和应用ID查询分页列表
	 * @param account 登录名
	 * @param appId 应用ID
	 * @param pageNo 分页起始
	 * @param pageSize 分页记录数
	 * @return
	 */
	public Pagination<UserOnlineDTO> findPagination(Pagination<UserOnlineDTO> p);
	
	
	/**
	 * 强制踢出登录用户
	 * 
	 * @param account 账户
	 */
	public void kickoutByAccount(String account);
	
	/**
	 * 检查账户是否已登录
	 * 
	 * @param account 登录账号
	 * @return
	 */
	public boolean hasLogin(String account);
	
	/**
	 * 强制踢出登录用户
	 * 
	 * @param sessionId 会话ID
	 */
	public void kickoutBySessionId(String sessionId);
	
	
	/**
	 * 根据会话ID查询在线用户
	 * 
	 * @param sessionId
	 * @return
	 */
	public Session getSessionBysessionId(Serializable sessionId);
}
