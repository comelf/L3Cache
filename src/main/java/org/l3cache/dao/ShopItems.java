package org.l3cache.dao;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rss")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopItems {
	
	private Channel channel;
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Channel{
		private String title;
		private String link;
		private String description;
		private String lastBuildDate;
		private int total;
		private int start;
		private int display;
		private List<Item> item;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getLastBuildDate() {
			return lastBuildDate;
		}
		public void setLastBuildDate(String lastBuildDate) {
			this.lastBuildDate = lastBuildDate;
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
		public int getDisplay() {
			return display;
		}
		public void setDisplay(int display) {
			this.display = display;
		}
		public List<Item> getItem() {
			return item;
		}
		public void setItem(List<Item> item) {
			this.item = item;
		}
	}
}
