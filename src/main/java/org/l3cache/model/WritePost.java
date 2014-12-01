package org.l3cache.model;

import org.apache.ibatis.type.Alias;

@Alias("writePost")
public class WritePost {
	private String title;
	private String shopUrl;
	private String contents;
	private String imgUrl;
	private String price;
	private int id;
	
	public WritePost() {
		
	}
	
	public WritePost(String title, String shopUrl, String contents,
			String imgUrl, String price, int id) {
		// TODO Auto-generated constructor stub
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
