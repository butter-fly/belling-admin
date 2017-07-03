package com.belling.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belling.admin.service.UserService;
import com.belling.base.controller.BaseController;
import com.belling.base.model.ResponseResult;
import com.belling.base.provider.PasswordProvider;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月21日 上午8:40:00  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	/**
	 * 用户业务对象
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		Subject subjec = SecurityUtils.getSubject();
		if (subjec != null && subjec.isAuthenticated()) {
			return "redirect:/admin";
		}
		return "login";
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value="/do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult doLogin(HttpServletRequest req, String account, String  password, Boolean rememberMe) {
		ResponseResult result = userService.login(getIpAddr(req), account, PasswordProvider.encrypt(password), rememberMe);
		return result;
	}
}
