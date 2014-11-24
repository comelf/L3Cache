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
	private int writer;
	private int readCount;
	private int numLike;
	
	
	
	public Post(String title, String shopUrl, String contents, String imgUrl,
			String price, int writer) {
		super();
		this.title = title;
		this.shopUrl = shopUrl;
		this.contents = contents;
		this.imgUrl = imgUrl;
		this.price = price;
		this.writer = writer;
	}

	public Post(int pid, String title, String shopUrl, String contents,
			String imgUrl, String price, Date writeDate, int writer,
			int readCount, int numLike) {
		super();
		this.pid = pid;
		this.title = title;
		this.shopUrl = shopUrl;
		this.contents = contents;
		this.imgUrl = imgUrl;
		this.price = price;
		this.writeDate = writeDate;
		this.writer = writer;
		this.readCount = readCount;
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


	public int getWriter() {
		return writer;
	}


	public void setWriter(int writer) {
		this.writer = writer;
	}


	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getNumLike() {
		return numLike;
	}


	public void setNumLike(int numLike) {
		this.numLike = numLike;
	}
	
	
	
}
