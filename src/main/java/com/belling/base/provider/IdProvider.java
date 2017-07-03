package com.belling.base.provider;

import java.util.UUID;


/**  
 * <pre>
 * Description	UUId生成工具
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月22日 上午10:29:18  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
public class IdProvider {

	/**
	 * Description:通过uuid生成唯一的记录id
	 * @Version 1.0 2016-8-24下午8:40:48 
	 * @return 生成的id
	 */
	public static String createUUIDId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
}
