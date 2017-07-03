package com.belling.base.security;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.belling.admin.model.Role;
import com.belling.admin.model.User;
import com.belling.admin.service.PermissionService;
import com.belling.admin.service.RoleService;
import com.belling.admin.service.UserService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 
 * @author Chenjx
 * @version 1.0
 */
public class ShiroAuthRealm extends AuthorizingRealm {
	
	/**
	 * 用户业务对象
	 */
	@Autowired	
	private UserService userService; 
	
	
	/**
	 * 角色业务对象
	 */
	@Autowired	
	private RoleService roleService;
	
	/**
	 * 权限业务对象
	 */
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		return new SimpleAuthenticationInfo(token.getUsername(), new String(token.getPassword()), getName());
	}
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("开始授权查询");
		User curUser = userService.findByAccount(principals.getPrimaryPrincipal().toString());
		int userId = curUser.getId();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<Role> rolesList = roleService.selectRolesByUser(userId);
		List<Integer> roleIds = Lists.newArrayList();
		Set<String> roleCodes = Sets.newHashSet();
		if (null != rolesList && rolesList.size() > 0) {
			for (Role role : rolesList) {
				if (!Strings.isNullOrEmpty(role.getCode())) {
					roleIds.add(role.getId());
					roleCodes.add(role.getCode());
				}
			}
			info.addRoles(roleCodes);
		}
		
		// 权限
		Set<String> permissions = Sets.newHashSet();
		if (roleIds.size() > 0) {
			for (Integer roleId: roleIds) {
				List<String> dataList = permissionService.findPermissionsByRoleId(roleId);
				if(null != dataList && dataList.size() > 0) {
					permissions.addAll(dataList);
				}
			}
		}
		permissions.remove("");
		info.addStringPermissions(permissions);
		return info;
	}
}
