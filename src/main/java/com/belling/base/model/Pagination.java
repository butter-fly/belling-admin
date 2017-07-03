package com.belling.base.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页基类
 * 
 * @author Sunny
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> implements Serializable {
	
	/*** 序列化ID   ***/
	private static final long serialVersionUID = 2234283310680151858L;
	
	/** 默认显示页码数 */
	public static final int DEFAULT_OFFSET_SIZE = 3;
	
	/** 默认每页行数 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	/** 显示页码数 */
	private int offsetSize = DEFAULT_OFFSET_SIZE;
	
	/** 每页行数 */
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	/** 记录总数 */
	private long rowCount;
	
	/** 当前页码 */
	private int pageNo = 1;
	
	/** 当前页的数据 */
	private List<T> list;
	
	/**
	 * @param pageNo
	 * @param pageSize
	 */
	public Pagination(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	/**
	 * 第一条数据位置
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 获取总页数
	 */
	public long getPageCount() {
		if (rowCount % pageSize == 0)
			return rowCount / pageSize;
		else
			return (rowCount / pageSize) + 1;
	}
}