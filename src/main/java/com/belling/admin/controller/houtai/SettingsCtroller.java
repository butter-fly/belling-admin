package com.belling.admin.controller.houtai;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belling.admin.service.SettingsService;
import com.belling.base.controller.BaseController;
import com.belling.base.model.ResponseResult;

/**  
 * <pre>
 * Description	系统设置标签
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
@RequestMapping("/admin/settings")
public class SettingsCtroller extends BaseController {
	
	/**
	 * 设置业务对象
	 */
	@Autowired
	private SettingsService settingsService;
	
	
	/**
	 * 系统设置
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "/settings/index";
	}
	
	 
	/**
	 * 保存设置
	 * 
	 * @return ResponseResult
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ResponseResult resetPwd(HttpServletRequest request) {
		Map<String, String> maps = getRequestMap(request);
		settingsService.update(maps);
		return ResponseResult.createSuccessResult().setMessage("修改成功");
	}
}
