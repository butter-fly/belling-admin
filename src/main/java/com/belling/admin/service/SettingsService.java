package com.belling.admin.service;

import java.util.List;
import java.util.Map;

import com.belling.admin.model.Settings;

/**  
 * <pre>
 * Description	
 * Copyright:	Copyright (c)2017  
 * Company:		Sunny
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月29日 下午8:56:42  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
public interface SettingsService {
	
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
	public String getValueByKey(String key);
	
	
	/**
	 * 批量更新key-value值
	 * 
	 * @param settingsMapper
	 */
	public void update(Map<String, String> maps);
	
	
	/**
	 * 更新key-value
	 * 
	 * @param key
	 * @param val
	 * @return
	 */
	public int updateForKey(String key, String val);
}
