package com.belling.base.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

/**  
 * <pre>
 * Description	Cache KEY
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月30日 上午9:42:18  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
public class MyKeyGenerator implements KeyGenerator {

	/* (non-Javadoc)
	 * @see org.springframework.cache.interceptor.KeyGenerator#generate(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object generate(Object target, Method method, Object... params) {
		if (params.length == 0) {
			return target.getClass().getName() + "." +method.getName();
		}
		if (params.length == 1) {
			Object param = params[0];
			if (param != null && !param.getClass().isArray()) {
				return target.getClass().getName() + "." + method.getName() + param;
			}
		}
		return target.getClass().getName() + "." + method.getName();
	}

}
