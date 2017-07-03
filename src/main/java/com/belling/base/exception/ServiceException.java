package com.belling.base.exception;

import com.belling.base.contants.ResponseCode;


/**
 * 业务逻辑异常
 * 
 * @author Sunny
 */
public class ServiceException extends BaseException{

	private static final long serialVersionUID = -2678203134198782909L;
	
	public static final String MESSAGE = "业务逻辑异常";
	
	public ServiceException() {
		super(MESSAGE);
	}

	public ServiceException(String message) {
		super(message);
		this.code = ResponseCode.SERVICE_ERROR;
	}
	
	public ServiceException(int code, String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.code = ResponseCode.SERVICE_ERROR;
	}
	
	public ServiceException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ServiceException(Throwable cause) {
		super(cause);
		this.code = ResponseCode.SERVICE_ERROR;
	}
}
