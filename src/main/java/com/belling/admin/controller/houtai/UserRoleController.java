package com.belling.admin.controller.houtai;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belling.admin.model.Role;
import com.belling.admin.model.UserRole;
import com.belling.admin.service.RoleService;
import com.belling.admin.service.UserRoleService;
import com.belling.base.controller.BaseController;
import com.belling.base.model.ResponseResult;
import com.google.common.collect.Lists;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月23日 上午9:39:19  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Controller
@RequestMapping("/admin/userRole")
public class UserRoleController extends BaseController {
	
	/**
	 * 角色业务对象
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 * 用户与角色关联业务对象
	 */
	@Autowired
	private UserRoleService userRoleService;

	/**
	 * 用户授权角色
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/allocate", method = RequestMethod.GET)
	public String allocate(Integer userId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("roleList", getRoleList(userId));
		return "/user/role-allocate";
	}
	
	/**
	 * 授权更新或保存
	 * 
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "/allocate", method = RequestMethod.POST)
	public @ResponseBody ResponseResult allocate(Integer userId, String roleIds) {
		List<Integer> idList = getAjaxIds(roleIds);
		List<UserRole> list = Lists.newArrayList();
		UserRole bean = null;
		for (Integer roleId : idList) {
			bean = new UserRole();
			bean.setUserId(userId);
			bean.setRoleId(roleId);
			list.add(bean);
		}
		userRoleService.allocate(userId,list);
		return ResponseResult.createSuccessResult().setMessage("授权成功");
	}
	
	
	/**
	 * 数据转换
	 * 
	 * @param userId
	 * @return
	 */
	private List<Role> getRoleList(Integer userId) {
		List<Role> list = roleService.findAll(true);
		for (Role role : list) {
			UserRole userRole = userRoleService.findByUserRoleId(userId, role.getId());
			if (null != userRole) {
				role.setIsChecked(true);
			}
			else {
				role.setIsChecked(false);
			}
		}
		return list;
	}

}
