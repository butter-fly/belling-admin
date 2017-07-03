package com.belling.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belling.admin.mapper.PermissionMapper;
import com.belling.admin.mapper.RolePermissionMapper;
import com.belling.admin.model.Permission;
import com.belling.admin.service.PermissionService;
import com.belling.base.layerui.NavChild;
import com.belling.base.layerui.NavNode;
import com.belling.base.service.impl.BaseServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


 

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017 
 * Company:		Sunny
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月22日 下午9:40:33  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission, Integer> implements PermissionService {
	
	/**
	 * 角色与权限Mapper对象
	 */
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#setMapper(com.belling.base.mapper.BaseMapper)
	 */
	@Autowired
	public void setMapper(PermissionMapper mapper) {
		this.mapper = mapper;
	}

	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#save(com.belling.base.model.PersistentObject)
	 */
	public void save(Permission t) {
		super.save(t);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.PermissionService#findByName(java.lang.String, java.lang.Integer, java.lang.Boolean)
	 */
	public List<Permission> findByName(String name, Boolean isEnable) {
		return mapper.findByName(name, isEnable);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.PermissionService#deletePermission(java.lang.Integer, java.lang.Integer)
	 */
	@Transactional
	public void deletePermission(Integer id) {
		List<Integer> idList = Lists.newArrayList();
		List<Permission> list = mapper.findByName(null, null);
		loopSubList(id, idList, list);
		idList.add(id);
		// 删除中间表
		rolePermissionMapper.deleteByPermissionIds(idList);
		verifyRows(mapper.deleteById(idList), idList.size(), "权限数据库删除失败");
	}

	// 递归方法，删除子权限
	protected void loopSubList(Integer id, List<Integer> idList, List<Permission> list) {
		for (Permission p : list) {
			if (id.equals(p.getPId())) {
				idList.add(p.getId());
				loopSubList(p.getId(), idList, list);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.PermissionService#findPermissionsByRoleId(java.lang.Integer)
	 */
	@Override
	public List<String> findPermissionsByRoleId(Integer roleId) {
		return mapper.findPermissionsByRoleId(roleId);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.PermissionService#findListByUserId(java.lang.Integer)
	 */
	@Override
	public List<Permission> findListByUserId(Integer userId) {
		return mapper.findListByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.PermissionService#getNavMenu(java.lang.Integer)
	 */
	@Override
	public List<NavNode> getNavMenu(Integer userId) {
		// 全部权限
		List<Permission> permissionsList  = findListByUserId(userId);
		
		// 父节点
		Map<Integer, NavNode> parentMap = Maps.newHashMap();
		
		// 子节点
		Map<Integer, Set<NavChild>> childMap = Maps.newHashMap();
		
		// 分散
		permissionsList.forEach((Permission p) -> {
			Integer pId = p.getPId();
			if (null == pId) {
				parentMap.put(p.getId(), NavNode.builder()
						.title(p.getName())
						.spread(false)
						.icon(p.getIcon())
						.build());
			} else {
				NavChild child = NavChild.builder()
						.title(p.getName())
						.icon(p.getIcon())
						.href(p.getUrl())
						.sort(p.getSort())
						.build();
				Set<NavChild> childData = null;
				if (childMap.containsKey(pId)) {
					childData = childMap.get(pId);
				} else {
					childData = Sets.newHashSet();
				}
				childData.add(child);
				
				// 分组
				childMap.put(pId, childData);
			}
		});
		
		// 分组
		parentMap.forEach((key,value) -> {
			Set<NavChild> childList = childMap.get(key);
			List<NavChild> sortList = Lists.newArrayList(childList);
			sortList(sortList, "sort", "ASC");
			value.setChildren(sortList);
			parentMap.put(key, value);
		});
		
		List<NavNode> nodes = new ArrayList<NavNode>(parentMap.values());
		// 默认展开第一个
		if (nodes.size() > 0) {
			NavNode first = nodes.get(0);
			first.setSpread(true);
			nodes.set(0,  first);
		}
		return nodes;
	}
}
