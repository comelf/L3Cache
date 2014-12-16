package org.l3cache.dao;

import core.utils.ResultCode;

public class Response {
	private int result;
	private String message;
	private int total;
	private Object data;
	
	public Response (int result) {
		this.result = result;
	}
	
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public static Response success() {
		return new Response(ResultCode.SUCCESS);
	}

	public static Response arguemnt_error() {
		return new Response(ResultCode.ARGUMENT_ERROR);
	}

	public static Response error() {
		return new Response(ResultCode.ERROR);
	}

	public static Response email_error() {
		return new Response(ResultCode.EMAIL_ERROR);
	}

	public static Response password_error() {
		return new Response(ResultCode.PASSWORD_ERROR);
	}

	public static Response adult_Query() {
		return new Response(ResultCode.ADULT_QUERY);
	}

	public static Response result_Zero() {
		Response response = new Response(ResultCode.SUCCESS);
		response.setMessage("검색결과 없음");
		response.setTotal(0);
		return response;
	}
}
