package org.l3cache.dao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdultResult {
	
	@XmlElement(name="item")
	private ItemA item;
	
	public ItemA getItem() {
		return item;
	}

	public void setItem(ItemA item) {
		this.item = item;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ItemA{
		private int adult;

		public int getAdult() {
			return adult;
		}

		public void setAdult(int adult) {
			this.adult = adult;
		}
		
	}
	
}
