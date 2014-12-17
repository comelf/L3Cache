package org.l3cache.dto;

import java.util.List;

import org.l3cache.model.Post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value="data")
public class PostsResult {
	private int start;
	private int total;
	private List<Post> list;
	
	public PostsResult(int start, int total, List<Post> list) {
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
	public List<Post> getList() {
		return list;
	}
	public void setList(List<Post> list) {
		this.list = list;
	}
	
}
