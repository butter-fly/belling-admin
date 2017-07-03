/**
 * 
 */
package com.belling.base.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * <pre>
 * Description	分页响应业务对象
 * Copyright:	Copyright (c)2016
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2016年3月29日 上午10:46:11  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------
 * 
 * </pre>
 */
@Builder
@ToString
@Data
public class TablePageResult implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -1635173003385837837L;

	private long draw; // Client request times
	private long recordsTotal; // Total records number without conditions
	private long recordsFiltered; // Total records number with conditions
	private Object data; // The data we should display on the page
	
	/**
	 * 构建数据
	 * 
	 * @param page
	 * @param draw
	 * @return
	 */
	public static TablePageResult createSuccessResult(Object data, long rowCount, long draw) {
		TablePageResult result = TablePageResult.builder().draw(draw)
				.data(data)
				.recordsTotal(rowCount)
				.recordsFiltered(rowCount)
				.build();
		return result;
	}
}
