package org.l3cache.dto;

import core.utils.ResultCode;

public class Status {
	private int status;
	private int id;
	
	public Status(int s) {
		this.status = s;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public static Status success() {
		return new Status(ResultCode.SUCCESS);
	}

	public static Status error() {
		return  new Status(ResultCode.ERROR);
	}

	public static Status emailDuplication() {
		return  new Status(ResultCode.EMAIL_DUPLICTION);
	}

	public static Status emailError() {
		return  new Status(ResultCode.EMAIL_ERROR);
	}

	public static Status passwordError() {
		return  new Status(ResultCode.PASSWORD_ERROR);
	}

	public static Status successAndLogin(int userId) {
		Status status = new Status(ResultCode.SUCCESS);
		status.setId(userId);
		return status;
	}

	public static Status argumentError() {
		return new Status(ResultCode.ARGUMENT_ERROR);
	}

}
