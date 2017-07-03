package com.belling.base.exception;

import lombok.Getter;
import lombok.Setter;


/**
 * 应用异常
 * 
 * @author Sunny
 */
public class BaseException extends RuntimeException{

	private static final long serialVersionUID = -2678203134198782909L;
	
	public static final String MESSAGE = "应用异常";
	
	@Setter
	@Getter
	protected int code = 0;

	public BaseException() {
		super(MESSAGE);
	}

	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BaseException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
}
