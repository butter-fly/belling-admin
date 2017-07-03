package com.belling.base.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * 
 * 类名：ServletUtil
 * 功能：Servlet工具类
 * 详细：获取ServletContext session，request  ApplicationContext
 * 作者：Chenjx
 * 版本：1.0
 * 日期：2017-5-13 下午3:27:40
 *
 */
public final class ServletUtil  {
		
 
	/**
	 * 获取 Session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	/**
	 * 获取 Session保存的数据
	 * @param name
	 * @return
	 */
	public static Object getSessionAttribute(String name){
		return getSession().getAttribute(name);
	}
	 
	
	/**
	 * 判断需要返回的信息格式是不是json
	 * @param request
	 * @return
	 */
	public static  boolean isJSONResponse(HttpServletRequest request){
		//根据Accept 类型判断返回的信息格式 
		String accept = request.getHeader("Accept");
		if(accept == null){
			return true;//如果Accept 为空 则返回 json格式
		}else{
			if(accept.contains("application/json")){
				return true;
			} else if("1".equals(request.getParameter("ajax"))){
				//iframe方式上传表单，默认隐藏提交一个ajax参数
				return true;
			}
		}
		return false;
	}
}
