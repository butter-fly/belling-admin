package com.belling.base.config;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * 支持Properties文件动态参数(仅支持一级，多级需自行扩展)
 * 
 * @author Sunny
 */
public class DynamicPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	/**
	 * 前缀
	 */
	private static final String DYNAMIC_PROPERTY_PREFIX = "${";
	
	/**
	 * 后缀
	 */
	private static final String DYNAMIC_PROPERTY_SUFFIX = "}";
	private Set<String> tempSet = Sets.newHashSet();
	private Map<String, String> dataMap = Maps.newHashMap();

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.PropertyResourceConfigurer#convertProperty(java.lang.String, java.lang.String)
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (propertyValue.contains(DYNAMIC_PROPERTY_PREFIX) && propertyValue.contains(DYNAMIC_PROPERTY_SUFFIX)) {
			tempSet.add(propertyName);
		} else {
			dataMap.put(new StringBuilder().append(DYNAMIC_PROPERTY_PREFIX).append(propertyName).append(DYNAMIC_PROPERTY_SUFFIX).toString(), propertyValue);
		}
		return super.convertProperty(propertyName, propertyValue);
	}

	/**
	 * @param propertyValue
	 * @return
	 */
	private String replaceValue(String propertyValue) {
		for (String key : dataMap.keySet()) {
			propertyValue = propertyValue.replace(key, dataMap.get(key));
		}
		return propertyValue;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#processProperties(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, java.util.Properties)
	 */
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		Enumeration<?> propertyNames = props.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String propertyName = (String) propertyNames.nextElement();
			if (tempSet.contains(propertyName)) {
				props.setProperty(propertyName, replaceValue(props.getProperty(propertyName)));
			}
		}
		tempSet.clear();
		dataMap.clear();
		super.processProperties(beanFactoryToProcess, props);
	}
}
