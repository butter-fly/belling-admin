package com.belling.admin.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.belling.admin.model.LoginLog;
import com.belling.base.mapper.BaseMapper;
import com.belling.base.model.Pagination;

/**
 * 角色持久化接口
 * 
 * @author Sunny
 */
public interface LoginLogMapper extends BaseMapper<LoginLog, Integer> {
	
	/**
	 * 清理日志
	 * 
	 * @param startTime
	 * @return
	 */
	public int deleteByTime(@Param("startTime") Timestamp startTime);
	
	/**
	 * 分页
	 * 
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param p 分页对象
	 * @return
	 */
	public List<LoginLog> findPaginationByTime(@Param("userId") String userId,  @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime, Pagination<LoginLog> p);
	
}
