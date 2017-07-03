package com.belling.base.controller;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;


/**
 * Controller基类
 * 
 * @author Sunny
 */
public class BaseController {

	private Integer[] getAjaxIds(final String str, final String separator) {
		Integer[] ids = null;
		if (str != null) {
			String[] strs = str.split(separator);
			ids = new Integer[strs.length];
			for (int i = 0, length = strs.length; i < length; i++) {
				ids[i] = Integer.valueOf(strs[i]);
			}
		}
		return ids;
	}

	protected List<Integer> getAjaxIds(final String ids) {
		return Strings.isNullOrEmpty(ids) ? new ArrayList<Integer>(0) : Arrays.asList(getAjaxIds(ids, ","));
	}
	

	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	protected String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return "unknown";
		}
		String ip = request.getHeader("x-forwarded-for");
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("X-Real-IP");
		}

		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
			if (("127.0.0.1".equals(ip)) || ("0:0:0:0:0:0:0:1".equals(ip))) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		return ip;
	}
	
	/**
	 * 获取QueryString的参数
	 * 
	 * @param request web请求
	 * @param prefix 前缀字符
	 * @return Map<String, String> 参数键值集合
	 */ 
	protected Map<String, String> getRequestMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		String name;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			map.put(name.trim(), request.getParameter(name));
		}
		return map;
	}
	
	/**
	 * 判断是否为手机浏览器请求
	 * 
	 * @return
	 */
	protected boolean isMobileRequest(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		return userAgent.contains("Mobile") && !userAgent.contains("iPad");
	}
}