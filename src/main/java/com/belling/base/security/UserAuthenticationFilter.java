/**  
 * @Title UserAuthenticationFilter.java
 * @date 2017-12-4 上午10:46:12
 * @Copyright: 2017 
 */
package com.belling.base.security;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;


/**
 * 验证用户是否在线 用户已验证通过
 * 
 * @author Chenjx
 * @version 1.0
 */
public class UserAuthenticationFilter extends UserFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.web.filter.authc.UserFilter#isAccessAllowed(javax.servlet
	 * .ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("验证登陆信息==" + req.getRequestURI());
		System.out.println("sessionId==" + req.getSession().getId());
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			if (currentUser.isAuthenticated() || currentUser.isRemembered()) {
				return true;
			} 
		}
		// 返回false表示不执行后续的过滤器
		return false;
	}
}
