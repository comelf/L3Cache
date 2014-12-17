package org.l3cache.model;

import org.apache.ibatis.type.Alias;

@Alias("writePost")
public class WritePost {
	private long pid;
	private String title;
	private String shopUrl;
	private String contents;
	private String imgUrl;
	private int price;
	private int writer;
	
	public WritePost() {
		
	}
	
	public WritePost(String title, String shopUrl, String contents,
			String imgUrl, int price, int writer) {
		this.title = title;
		this.shopUrl = shopUrl;
		this.contents = contents;
		this.imgUrl = imgUrl;
		this.price = price;
		this.writer = writer;
	}
	
	public WritePost(long pid, String title, String shopUrl, String contents,
			String imgUrl, int price, int writer) {
		this.pid = pid;
		this.title = title;
		this.shopUrl = shopUrl;
		this.contents = contents;
		this.imgUrl = imgUrl;
		this.price = price;
		this.pid = pid;
	}
	
	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}
	
	public int getWriter() {
		return writer;
	}

	public void setWriter(int writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isValidated() {
		if(this.title.isEmpty()){
			return false;
		}
		if(this.shopUrl.isEmpty()){
			return false;
		}
		if(this.imgUrl.isEmpty()){
			return false;
		}
		if(this.price<=0){
			return false;
		}
		if(this.writer<=0){
			return false;
		}
		
		return true;
	}

	
}
