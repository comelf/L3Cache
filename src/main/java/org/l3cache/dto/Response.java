package org.l3cache.dto;

import core.utils.ResultCode;

public class Response {
	private int result;
	private String message;
	private long total;
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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	public static Response success() {
		return new Response(ResultCode.SUCCESS);
	}

	public static Response arguemntError() {
		return new Response(ResultCode.ARGUMENT_ERROR);
	}

	public static Response error() {
		return new Response(ResultCode.ERROR);
	}

	public static Response emailError() {
		return new Response(ResultCode.EMAIL_ERROR);
	}

	public static Response passwordError() {
		return new Response(ResultCode.PASSWORD_ERROR);
	}

	public static Response adultQuery() {
		return new Response(ResultCode.ADULT_QUERY);
	}

	public static Response resultZero() {
		Response response = new Response(ResultCode.SUCCESS);
		response.setMessage("검색결과 없음");
		response.setTotal(0);
		return response;
	}
}
