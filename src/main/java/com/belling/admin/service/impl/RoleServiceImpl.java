package com.belling.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belling.admin.mapper.RoleMapper;
import com.belling.admin.mapper.RolePermissionMapper;
import com.belling.admin.mapper.UserRoleMapper;
import com.belling.admin.model.Role;
import com.belling.admin.service.RoleService;
import com.belling.base.model.Pagination;
import com.belling.base.service.impl.BaseServiceImpl;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017 
 * Company:		Sunny
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月22日 下午10:04:38  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role, Integer> implements RoleService {

	/**
	 * 
	 */
	@Resource
	private UserRoleMapper userRoleMapper;
	
	/**
	 * 
	 */
	@Resource
	private RolePermissionMapper rolePermissionMapper;

	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#setMapper(com.belling.base.mapper.BaseMapper)
	 */
	@Autowired
	public void setMapper(RoleMapper mapper) {
		this.mapper = mapper;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.RoleService#enable(java.lang.Boolean, java.util.List)
	 */
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(mapper.enable(isEnable, idList), idList.size(), "角色数据库更新失败");
	}

	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#save(com.belling.base.model.PersistentObject)
	 */
	public void save(Role t) {
		super.save(t);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.RoleService#findPaginationByName(java.lang.String, java.lang.Integer, com.belling.base.model.Pagination)
	 */
	public Pagination<Role> findPaginationByName(String name, Pagination<Role> p) {
		mapper.findPaginationByName(name, null, p);
		return p;
	}


	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#deleteById(java.util.List)
	 */
	@Transactional
	public void deleteById(List<Integer> idList) {
		userRoleMapper.deleteByRoleIds(idList);
		rolePermissionMapper.deleteByRoleIds(idList);
		verifyRows(mapper.deleteById(idList), idList.size(), "角色数据库删除失败");
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.RoleService#findAll(java.lang.Boolean)
	 */
	@Override
	public List<Role> findAll(Boolean isEnable) {
		return mapper.findPaginationByName(null, isEnable, null);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.RoleService#selectRolesByUser(java.lang.Integer)
	 */
	@Override
	public List<Role> selectRolesByUser(Integer userId) {
		return mapper.selectRolesByUser(userId);
	}
}
