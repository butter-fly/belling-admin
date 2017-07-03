package com.belling.admin.service;

import java.util.List;

import com.belling.admin.model.UserRole;
import com.belling.base.service.BaseService;

/**
 * 管理员角色映射服务接口
 * 
 * @author Sunny
 */
public interface UserRoleService extends BaseService<UserRole, Integer> {
	
	/**
	 * 根据管理员ID和角色ID查询映射
	 * @param userId 管理员ID
	 * @param roleId 角色ID
	 * @return
	 */
	public UserRole findByUserRoleId(Integer userId, Integer roleId);
	
	/**
	 * 根据管理员ID给管理员分配角色
	 * @param userId 管理员ID
	 * @param list 管理员角色映射集合
	 * @return
	 */
	public void allocate(Integer userId, List<UserRole> list);
	
	/**
	 * 根据角色ID集合删除映射
	 * @param idList 角色ID集合
	 * @return
	 */
	public void deleteByRoleIds(List<Integer> idList);
	
	/**
	 * 根据管理员ID集合删除映射
	 * @param idList 管理员ID集合
	 * @return
	 */
	public void deleteByUserIds(List<Integer> idList);
	
	
	/**
	 * 分配App时，删除无效的userRole
	 * @param userId 应用ID
	 * @return
	 */
	public void deleteForChangeUser(Integer userId);
}
