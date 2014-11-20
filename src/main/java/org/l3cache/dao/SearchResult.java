package org.l3cache.dao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value="data")
public class SearchResult {
	private int start;
	private int total;
	private List<Item> list;
	
	public SearchResult(int start, int total, List<Item> list) {
		this.start = start;
		this.total = total;
		this.list = list;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}	
	public List<Item> getList() {
		return list;
	}
	public void setList(List<Item> list) {
		this.list = list;
	}
	
}
