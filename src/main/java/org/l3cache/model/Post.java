package org.l3cache.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("post")
public class Post {
	private long pid;
	private String title;
	private String shopUrl;
	private String contents;
	private String imgUrl;
	private String price;	
	private Date writeDate;
	private String writer;
	private int read;
	private int numLike;
	private int userLike;
	
	public Post(String title, String shopUrl, String contents, String imgUrl,
			String price, String id) {
		super();
		this.title = title;
		this.shopUrl = shopUrl;
		this.contents = contents;
		this.imgUrl = imgUrl;
		this.price = price;
		this.writer = id;
	}

	public Post(int pid, String title, String shopUrl, String contents,
			String imgUrl, String price, Date writeDate, String writer,
			int read, int numLike) {
		super();
		this.pid = pid;
		this.title = title;
		this.shopUrl = shopUrl;
		this.contents = contents;
		this.imgUrl = imgUrl;
		this.price = price;
		this.writeDate = writeDate;
		this.writer = writer;
		this.read = read;
		this.numLike = numLike;
	}

	public Post(){
		
	}


	public long getPid() {
		return pid;
	}


	public void setPid(long pid) {
		this.pid = pid;
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


	public Date getWriteDate() {
		return writeDate;
	}


	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getNumLike() {
		return numLike;
	}


	public void setNumLike(int numLike) {
		this.numLike = numLike;
	}

	public int getLike() {
		return userLike;
	}

	public void setLike(int userLike) {
		this.userLike = userLike;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}
	
	
	
}
