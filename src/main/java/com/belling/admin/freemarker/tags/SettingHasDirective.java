package com.belling.admin.freemarker.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.belling.admin.service.SettingsService;
import com.google.common.base.Strings;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * <pre>
 * Description	设置参数自定义标签
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月29日 下午10:06:47  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */

public class SettingHasDirective implements TemplateDirectiveModel {

	/**
	 * 设置业务对象
	 */
	@Autowired
	private SettingsService settingsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
	 * Environment, java.util.Map, freemarker.template.TemplateModel[],
	 * freemarker.template.TemplateDirectiveBody)
	 */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Override
	public void execute(Environment env, Map params, TemplateModel model[], TemplateDirectiveBody body) throws TemplateException, IOException {
		if (body == null) {// 自定义标签必须有内容，即自定义
			throw new TemplateModelException("null body");
		} else {
			TemplateModel timestampModel = (TemplateModel) params.get("key");
			String key = timestampModel.toString();
			if (Strings.isNullOrEmpty(key)) {
				Writer out = env.getOut();
				body.render(out);
				return;
			}
			String val = settingsService.getValueByKey(key);
			env.setVariable("val",  ObjectWrapper.SIMPLE_WRAPPER.wrap(val));
			Writer out = env.getOut();
			body.render(out);
		}
	 
	}
}
