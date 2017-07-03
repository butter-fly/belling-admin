package com.belling.admin.service;

import java.util.List;

import com.belling.admin.model.Role;
import com.belling.base.model.Pagination;
import com.belling.base.service.BaseService;

/**
 * 角色服务接口
 * 
 * @author Sunny
 */
public interface RoleService extends BaseService<Role, Integer> {
	
	/**
	 * 启用禁用操作
	 * @param isEnable 是否启用
	 * @param idList 角色ID集合
	 * @return
	 */
	public void enable(Boolean isEnable, List<Integer> idList);
	
	/**
	 * 根据角色名称和应用ID查询分页列表
	 * @param name 角色名称
	 * @param appId 应用ID
	 * @param pageNo 分页起始
	 * @param pageSize 分页记录数
	 * @return
	 */
	public Pagination<Role> findPaginationByName(String name, Pagination<Role> p);
	
	/**
	 * 查询应用可用角色
	 * @param isEnable 是否启用
	 * @return
	 */
	public List<Role> findAll(Boolean isEnable);
	
	/**
	 * 根据用户ID查询角色集合
	 * 
	 * @param userId 用户ID
	 * @return
	 */
	public List<Role> selectRolesByUser(Integer userId);
}
