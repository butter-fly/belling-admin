package com.belling.base.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;


/**  
 * <pre>
 * Description	资源路径视图统一处理
 * Copyright:	Copyright (c)2016
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2016年6月30日 上午10:34:09  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
public class RichFreeMarkerView extends FreeMarkerView {
	
	/** 
	 * 部署路径属性名称 
	 */ 
	public static final String CONTEXT_PATH = "base";
	
	/**
	 * 时间戳
	 */
	public static final String REQUEST_TIME = "qt";
	
	/**
	 * 系统版本号
	 */
	public static final String SYS_NOW_VERSION = "version";
	
	/** 
	 * 在model中增加部署路径base，方便处理部署路径问题。
	 */ 
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	protected void exposeHelpers(Map model, HttpServletRequest request) throws Exception {
		super.exposeHelpers(model, request);
		model.put(CONTEXT_PATH, request.getContextPath());
		model.put(REQUEST_TIME, String.valueOf(System.currentTimeMillis()));
		model.put(SYS_NOW_VERSION,  "2.1.0.1");
	} 
} 