package com.belling.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belling.admin.mapper.RolePermissionMapper;
import com.belling.admin.model.RolePermission;
import com.belling.admin.service.RolePermissionService;
import com.belling.base.service.impl.BaseServiceImpl;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2014  
 * Company:		Sunny
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月22日 下午10:05:46  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionMapper, RolePermission, Integer> implements RolePermissionService {
	
	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#setMapper(com.belling.base.mapper.BaseMapper)
	 */
	@Autowired
	public void setMapper(RolePermissionMapper mapper) {
		this.mapper = mapper;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.RolePermissionService#allocate(java.lang.Integer, java.util.List)
	 */
	@Transactional
	public void allocate(Integer roleId, List<RolePermission> list) {
		mapper.deleteByRoleIds(Arrays.asList(roleId));
		super.save(list);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.RolePermissionService#findByRoleId(java.lang.Integer)
	 */
	public List<RolePermission> findByRoleId(Integer roleId) {
		return mapper.findByRoleId(roleId);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.RolePermissionService#deleteByPermissionIds(java.util.List)
	 */
	public void deleteByPermissionIds(List<Integer> idList) {
		mapper.deleteByPermissionIds(idList);
	}
	
	/* (non-Javadoc)
	 * @see com.belling.admin.service.RolePermissionService#deleteByRoleIds(java.util.List)
	 */
	public void deleteByRoleIds(List<Integer> idList) {
		mapper.deleteByRoleIds(idList);
	}
}
