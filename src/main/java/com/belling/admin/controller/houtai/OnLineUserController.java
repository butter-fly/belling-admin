package com.belling.admin.controller.houtai;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belling.admin.dto.UserOnlineDTO;
import com.belling.admin.service.UserOnlineService;
import com.belling.base.controller.BaseController;
import com.belling.base.model.Pagination;
import com.belling.base.model.ResponseResult;
import com.belling.base.model.TablePageResult;
import com.google.common.base.Strings;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月29日 上午9:38:36  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Controller
@RequestMapping("/admin/online")
public class OnLineUserController extends BaseController {

	/**
	 * Shiro用户会话对象
	 */
	@Autowired
	private UserOnlineService userOnlineService;
	
	/**
	 * 用户列表首页
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:online:list")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "/online/list";
	}
	
	/**
	 * 获取全部在线用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public  @ResponseBody TablePageResult getAllOnlineUsers(Integer start, Integer length, Integer draw) {
		try {
			if (start <= 0) {
				start = 0;
			}
			if (length <=  0) {
				length = 8;
			}
			Pagination<UserOnlineDTO> page = new Pagination<UserOnlineDTO>((start / length) + 1, length);
			page = userOnlineService.findPagination(page);
			return TablePageResult.createSuccessResult(page.getList(), page.getRowCount(), draw + 1);
		} catch(Exception ex) {
			// 捕获因未知异常处理响应数据格式不对，导致tabels js报错问题
			return TablePageResult.createSuccessResult(new ArrayList<UserOnlineDTO>(), 0, draw + 1);
		}
	}
	
	/**
	 * 踢出在线用户
	 * 
	 * @param sessionId 在线用户会话ID
	 * @return
	 */
	@RequestMapping(value = "/batchkickout")
	public @ResponseBody ResponseResult  batchKickout(String sessionId) {
		if (Strings.isNullOrEmpty(sessionId)) {
			return ResponseResult.createErrorResult().setMessage("非法参数");
		}
		userOnlineService.kickoutBySessionId(sessionId);
		return ResponseResult.createSuccessResult().setMessage("用户已踢出");
	}
	
	/**
	 * 踢出在线用户
	 * 
	 * @param account 在线用户会话ID
	 * @return
	 */
	@RequestMapping(value = "/kickout")
	public @ResponseBody ResponseResult  kickout(String sessionId) {
		if (Strings.isNullOrEmpty(sessionId)) {
			return ResponseResult.createErrorResult().setMessage("非法参数");
		}
		String  curSessionId = SecurityUtils.getSubject().getSession().getId().toString();
		if (curSessionId.equals(sessionId))  {
			return ResponseResult.createErrorResult().setMessage("不能下线自己");
		}
		userOnlineService.kickoutBySessionId(sessionId);
		return ResponseResult.createSuccessResult().setMessage("用户已被下线");
	}
}
