package com.belling.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belling.admin.mapper.UserRoleMapper;
import com.belling.admin.model.UserRole;
import com.belling.admin.service.UserRoleService;
import com.belling.base.service.impl.BaseServiceImpl;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月22日 下午10:05:33  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole, Integer> implements UserRoleService {

	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#setMapper(com.belling.base.mapper.BaseMapper)
	 */
	@Autowired
	public void setMapper(UserRoleMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * @param userId
	 * @param appId
	 * @param list
	 */
	@Transactional
	public void allocate(Integer userId, List<UserRole> list) {
		mapper.deleteByUserIds(Arrays.asList(userId));
		super.save(list);
	}
	
	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserRoleService#findByUserRoleId(java.lang.Integer, java.lang.Integer)
	 */
	public UserRole findByUserRoleId(Integer userId, Integer roleId) {
		return mapper.findByUserRoleId(userId, roleId);
	}
	
	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserRoleService#deleteByRoleIds(java.util.List)
	 */
	public void deleteByRoleIds(List<Integer> idList) {
		mapper.deleteByRoleIds(idList);
	}
	
	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserRoleService#deleteByUserIds(java.util.List, java.lang.Integer)
	 */
	public void deleteByUserIds(List<Integer> idList) {
		mapper.deleteByUserIds(idList);
	}
	
	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserRoleService#deleteForChangeApp(java.lang.Integer)
	 */
	public void deleteForChangeUser(Integer userId) {
		mapper.deleteForChangeUser(userId);
	}
}
