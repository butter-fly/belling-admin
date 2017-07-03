package com.belling.base.exception;

import com.belling.base.contants.ResponseCode;


/**
 * 验证异常
 * 
 * @author Sunny
 */
public class ValidateException extends BaseException {

	private static final long serialVersionUID = 5214146953001236471L;

	public static final String MESSAGE = "验证异常";

	public ValidateException() {
		super(MESSAGE);
	}

	public ValidateException(String message) {
		super(message);
		this.code = ResponseCode.VALIDATE_ERROR;
	}
	
	public ValidateException(int code, String message) {
		super(message);
		this.code = code;
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
		this.code = ResponseCode.VALIDATE_ERROR;
	}
	
	public ValidateException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ValidateException(Throwable cause) {
		super(cause);
		this.code = ResponseCode.VALIDATE_ERROR;
	}
}
