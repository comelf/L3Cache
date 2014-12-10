package org.l3cache.model;

import org.apache.ibatis.type.Alias;

@Alias("writePost")
public class WritePost {
	private String title;
	private String shopUrl;
	private String contents;
	private String imgUrl;
	private int price;
	private long writer;
	
	public WritePost() {
		
	}
	
	public WritePost(String title, String shopUrl, String contents,
			String imgUrl, int price, long id) {
		this.title = title;
		this.shopUrl = shopUrl;
		this.contents = contents;
		this.imgUrl = imgUrl;
		this.price = price;
		this.writer = id;
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

	public long getId() {
		return writer;
	}

	public void setId(int writer) {
		this.writer = writer;
	}

	
}
