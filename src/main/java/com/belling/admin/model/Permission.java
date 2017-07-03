package com.belling.admin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.alibaba.fastjson.annotation.JSONField;
import com.belling.base.model.PersistentObject;

/**
 * 权限
 * 
 * @author Sunny
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Permission extends PersistentObject {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 4368792338865943489L;

	/** 父ID */
	private Integer pId;
	
	/** 图标 */
	@JSONField(serialize = false)
	private String icon;
	
	/** 名称 */
	private String name;
	
	/** 权限URL */
	@JSONField(serialize = false)
	private String url;
	
	/** 权限标志 */
	private String permission;
	
	/** 排序 */
	private Integer sort = Integer.valueOf(1);
	
	/** 是否菜单 */
	private Boolean isMenu;
	
	/** 是否启用 */
	private Boolean isEnable;
}
