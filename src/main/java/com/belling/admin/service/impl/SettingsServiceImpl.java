package com.belling.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.belling.admin.mapper.SettingsMapper;
import com.belling.admin.model.Settings;
import com.belling.admin.service.SettingsService;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2014  
 * Company:		墙角信息科技有限公司
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月29日 下午8:57:20  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Service("settingsService")
public class SettingsServiceImpl implements SettingsService {
	
	/**
	 * 设置Mapper对象
	 */
	@Autowired
	private SettingsMapper settingsMapper;

	/* (non-Javadoc)
	 * @see com.belling.admin.service.SettingsService#getAll()
	 */
	@Override
	public List<Settings> getAll() {
		return settingsMapper.getAll();
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.SettingsService#getValueByKey(java.lang.String)
	 */
	@Cacheable(value="settingsCache", key="#key")
	@Override
	public String getValueByKey(String key) {
		return settingsMapper.getValueByKey(key);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.SettingsService#update(com.sun.javafx.collections.MappingChange.Map)
	 */
	@CacheEvict(value="settingsCache", allEntries = true)
	public void update(Map<String, String> maps) {
		if(null == maps || maps.size() == 0) {
			return;
		}
		maps.forEach((key,val)->{
			settingsMapper.updateForKey(key, val);
		});
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.SettingsService#updateForKey(java.lang.String, java.lang.String)
	 */
	@CacheEvict(value="settingsCache", key="#key")
	@Override
	public int updateForKey(String key, String val) {
		return settingsMapper.updateForKey(key, val);
	}
}
