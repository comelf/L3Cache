package org.l3cache.model;

import org.apache.ibatis.type.Alias;

@Alias("postSel")
public class PostSel {

	private int start;
	private int uid;
	
	public PostSel() {
		
	}
	
	public PostSel(int start, int uid) {
		this.start = start;
		this.uid = uid;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
}
