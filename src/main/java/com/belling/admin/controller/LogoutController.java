package com.belling.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.belling.base.controller.BaseController;

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
@RequestMapping("/logout")
public class LogoutController extends BaseController {
	
	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
}
