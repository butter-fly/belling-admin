package com.belling.admin.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**  
 * <pre>
 * Description	系统设置参数
 * Copyright:	Copyright (c)2017  
 * Company:		Chenjx
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月29日 下午8:42:22  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Settings implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 9023004562741725912L;
	
	/**
	 * KEY
	 */
	private String key;
	
	
	/**
	 * VALUE
	 */
	private String val;
}
