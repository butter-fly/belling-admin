package com.belling.admin.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.belling.base.model.PersistentObject;


/**
 * 管理员
 * 
 * @author Sunny
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends PersistentObject {
	
	/** 序列化ID */
	private static final long serialVersionUID = 1106412532325860697L;
	
	/** 登录名 */
	private String account;
	
	/** 密码 */
	@JSONField(serialize = false)
	private String password;
	
	/** 最后登录IP */
	@JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
	private String lastLoginIp;
	
	/** 登录总次数 */
	private Integer loginCount = Integer.valueOf(0);
	
	/** 最后登录时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	
	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/** 是否启用 */
	private Boolean isEnable = true;
}
