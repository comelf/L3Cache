package org.l3cache.model;

import org.apache.ibatis.type.Alias;

@Alias("postId")
public class PostId {
	private long pid;
	private int uid;
	
	public PostId () {
		
	}

	public PostId (long pid, int uid) {
		this.pid = pid;
		this.uid = uid;
	}
	
	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
}
