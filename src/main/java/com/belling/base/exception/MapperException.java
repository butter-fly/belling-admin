package com.belling.base.exception;

import com.belling.base.contants.ResponseCode;


/**
 * 数据访问异常
 * 
 * @author Sunny
 */
public class MapperException extends BaseException {

	private static final long serialVersionUID = -7980532772047897013L;

	public static final String MESSAGE = "数据访问异常";

	public MapperException() {
		super(MESSAGE);
	}

	public MapperException(String message) {
		super(message);
		this.code = ResponseCode.DAO_ERROR;
	}
	
	public MapperException(int code, String message) {
		super(message);
		this.code = code;
	}

	public MapperException(String message, Throwable cause) {
		super(message, cause);
		this.code = 1000;
	}
	
	public MapperException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public MapperException(Throwable cause) {
		super(cause);
		this.code = ResponseCode.DAO_ERROR;
	}
}
