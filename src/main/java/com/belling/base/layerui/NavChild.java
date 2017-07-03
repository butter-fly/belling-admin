package com.belling.base.layerui;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**  
 * <pre>
 * Description	layui 
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月27日 下午3:11:35  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NavChild implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -3690553973115479338L;
	
	/** 垂直导航栏 标题  **/
	private String title;
	
	/** 垂直导航栏 ICON  **/
	private String icon;
	
	/** 垂直导航栏 链接地址 **/
	private String href;
	
	/** 排序 **/
	@JSONField(serialize = false)
	private Integer sort;
}
