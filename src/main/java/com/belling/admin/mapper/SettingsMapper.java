package com.belling.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.belling.admin.model.Settings;
import com.belling.base.mapper.BaseMapper;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Chenjx
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月29日 下午8:43:31  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

public interface SettingsMapper extends BaseMapper<Settings, Integer> {
	
	/**
	 * 获取全部的配置参数-值
	 * 
	 * @return
	 */
	public List<Settings> getAll();
	
	/**
	 * 通过Key查询Value
	 * 
	 * @param key
	 * @return
	 */
	public String getValueByKey(@Param("key") String key);
	
	/**
	 * 更新key-value
	 * 
	 * @param key
	 * @param val
	 * @return
	 */
	public int updateForKey(@Param("key") String key, @Param("val") String val);
}
