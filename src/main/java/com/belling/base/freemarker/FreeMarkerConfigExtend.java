package com.belling.base.freemarker;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**  
 * <pre>
 * Description	Freemark配置扩展
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月26日 下午4:33:47  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
public class FreeMarkerConfigExtend extends FreeMarkerConfigurer {
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		super.afterPropertiesSet();
		Configuration cfg = this.getConfiguration();
		cfg.setSharedVariable("shiro", new ShiroTags());// shiro标签
		cfg.setNumberFormat("#");// 防止页面输出数字,变成2,000
		// 可以添加很多自己的要传输到页面的[方法、对象、值]
	}
}
