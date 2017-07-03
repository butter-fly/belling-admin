package com.belling.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.belling.admin.model.Role;
import com.belling.base.mapper.BaseMapper;
import com.belling.base.model.Pagination;

/**
 * 角色持久化接口
 * 
 * @author Sunny
 */
public interface RoleMapper extends BaseMapper<Role, Integer> {

	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);

	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

	public List<Role> findPaginationByName(@Param("name") String name, @Param("isEnable") Boolean isEnable,  Pagination<Role> p);
	
	public List<Role> selectRolesByUser(@Param("userId") Integer userId);
}
