package com.belling.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.belling.admin.model.RolePermission;
import com.belling.base.mapper.BaseMapper;

/**
 * 角色权限映射持久化接口
 * 
 * @author Sunny
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission, Integer> {
	
	public List<RolePermission> findByRoleId(@Param("roleId") Integer roleId);
	
	public int deleteByPermissionIds(@Param("idList") List<Integer> idList);
	
	public int deleteByRoleIds(@Param("idList") List<Integer> idList);
}
