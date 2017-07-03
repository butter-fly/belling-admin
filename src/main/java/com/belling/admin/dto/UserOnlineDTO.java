package com.belling.admin.dto;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月29日 上午11:49:34  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOnlineDTO implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1078386810741308715L;
	
	//Session Id
	private String sessionId;
	
	//Session account
	private String loginAccount;
	
	//Session Host
	private String ip;
	
	//Session创建时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	//Session最后交互时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastAccess;
	
	//Session timeout
	private long timeout;
	
	//session 是否踢出
	private boolean sessionStatus = Boolean.TRUE;
}
