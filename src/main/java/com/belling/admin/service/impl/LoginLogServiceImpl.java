package com.belling.admin.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belling.admin.mapper.LoginLogMapper;
import com.belling.admin.model.LoginLog;
import com.belling.admin.service.LoginLogService;
import com.belling.base.model.Pagination;
import com.belling.base.service.impl.BaseServiceImpl;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月27日 下午4:43:45  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Service("loginLogService")
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogMapper, LoginLog, Integer> implements LoginLogService {
	
	
	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#setMapper(com.belling.base.mapper.BaseMapper)
	 */
	@Override
	@Autowired
	public void setMapper(LoginLogMapper mapper) {
		this.mapper = mapper;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.LoginLogService#findPaginationByTime(java.sql.Timestamp, com.belling.base.model.Pagination)
	 */
	@Override
	public Pagination<LoginLog> findPaginationByTime(String uId, Timestamp startTime, Timestamp endTime, Pagination<LoginLog> p) {
		mapper.findPaginationByTime(uId, startTime, endTime, p);
		return p;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.LoginLogService#deleteByTime(java.sql.Timestamp)
	 */
	@Override
	public void deleteByTime(Timestamp startTime) {
		mapper.deleteByTime(startTime);
	}
}
