package com.belling.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
 * Create at:	2017年6月20日 下午4:07:02  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	
	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String execute(HttpServletRequest request, ModelMap model) {
		// 设置登录用户名
		return "/admin";
	}
}
