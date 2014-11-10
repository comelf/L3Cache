package org.l3cache.dto;

import java.util.List;

public class Response {
	private int result;
	private int total;
	private int start;
	private List<Item> itemList;
	
	public Response(int code) {
		this.result = code;
	}

	public int getResultCode() {
		return result;
	}

	public void setResultCode(int result) {
		this.result = result;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setItemList(List<Item> subList) {
		this.itemList = subList;
	}
	
	public List<Item> getItemList() {
		return itemList;
	}
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
}
