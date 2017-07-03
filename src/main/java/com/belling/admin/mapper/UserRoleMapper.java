package com.belling.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.belling.admin.model.UserRole;
import com.belling.base.mapper.BaseMapper;

/**
 * 管理员角色映射持久化接口
 * 
 * @author Sunny
 */
public interface UserRoleMapper extends BaseMapper<UserRole, Integer> {

	public UserRole findByUserRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

	public int deleteByRoleIds(@Param("idList") List<Integer> idList);

	public int deleteByUserIds(@Param("idList") List<Integer> idList);

	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public int deleteForChangeUser(@Param("userId") Integer userId);
}
