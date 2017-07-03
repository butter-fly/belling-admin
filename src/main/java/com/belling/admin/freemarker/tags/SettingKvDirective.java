package com.belling.admin.freemarker.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.belling.admin.service.SettingsService;

import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;

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

public class SettingKvDirective implements TemplateDirectiveModel {

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
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment environment, Map map, TemplateModel atemplatemodel[],
			TemplateDirectiveBody templatedirectivebody) {
		Writer out = null;
		try {
			if (templatedirectivebody == null) {// 自定义标签必须有内容，即自定义
				throw new TemplateModelException("null body");
			} else {
				out = environment.getOut();
				
				TemplateNumberModel numberModel = (TemplateNumberModel) map.get("count");
				int count = numberModel.getAsNumber().intValue();
				for (int i = 0; i < count; i++) {
					if (atemplatemodel.length > 0) {
						atemplatemodel[0] = new SimpleNumber(i + 1);
					}
					templatedirectivebody.render(out);
				}
			}
		} catch (TemplateModelException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
