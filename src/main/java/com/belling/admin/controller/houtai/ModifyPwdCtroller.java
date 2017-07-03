package com.belling.admin.controller.houtai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belling.admin.model.User;
import com.belling.admin.service.UserService;
import com.belling.base.controller.BaseController;
import com.belling.base.exception.ValidateException;
import com.belling.base.model.ResponseResult;
import com.belling.base.provider.PasswordProvider;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月27日 下午4:48:39  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Controller
@RequestMapping("/admin/pwd")
public class ModifyPwdCtroller extends BaseController {
	
	/**
	 * 用户业务对象
	 */
	@Autowired
	private UserService userService;
	
	
	/**
	 * 修改密码
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "modify-pwd";
	}
	
	
	
	 
	/**
	 * 重置密码
	 * 
	 * @param uId 用户ID
	 * @param oldPassword 旧密码
	 * @param confirmPassword 确认密码
	 * @return ResponseResult
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public @ResponseBody ResponseResult resetPwd(Integer uId, String oldPassword, String password) {
		User user = userService.get(uId);
		if (null == user) {
			throw new ValidateException("用户不存在");
		}
		oldPassword = PasswordProvider.encrypt(oldPassword);
		if (!oldPassword.equals(user.getPassword())) {
			throw new ValidateException("旧密码输入不正确");
		}
		user.setPassword(PasswordProvider.encrypt(password));
		userService.update(user);
		return ResponseResult.createSuccessResult().setMessage("修改成功");
	}
}
