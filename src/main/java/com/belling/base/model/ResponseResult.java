package com.belling.base.model;

import com.belling.base.contants.ResponseCode;

/**
 * 返回结果
 * 
 * @author Sunny
 */
public class ResponseResult {

	/**
	 * 结果体
	 */
	protected Object data;

	/**
	 * 状态码
	 */
	protected Integer code;

	/**
	 * 信息
	 */
	protected String message;

	/**
	 * 
	 */
	private ResponseResult() {
		super();
	}

	/**
	 * @param code
	 */
	private ResponseResult(Integer code) {
		this.code = code;
	}

	public static ResponseResult create(Integer code) {
		return new ResponseResult(code);
	}

	public static ResponseResult createSuccessResult() {
		return create(ResponseCode.SUCCESS);
	}
	
	public static ResponseResult createErrorResult() {
		return create(ResponseCode.ERROR);
	}

	public static ResponseResult createSuccessResult(Object data, String message) {
		return createSuccessResult().setData(data).setMessage(message);
	}

	public boolean isSuccess() {
		return code != null && code.equals(ResponseCode.SUCCESS);
	}

	public Object getData() {
		return data;
	}

	public ResponseResult setData(Object data) {
		this.data = data;
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public ResponseResult setCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResponseResult setMessage(String message) {
		this.message = message;
		return this;
	}
}
