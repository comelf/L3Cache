package core.search;

import java.util.List;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.l3cache.dto.Item;
import org.l3cache.dto.ShopItems;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class SearchHelper {
	ApiCaller apiCaller;
	
	Jaxb2Marshaller unmarshaller;
	
	public SearchHelper(ApiCaller apiCaller, Jaxb2Marshaller jaxb2Marshaller) {
		this.apiCaller = apiCaller;
		this.unmarshaller = jaxb2Marshaller;
	}

	public StreamSource naverApiStream(Map<String, Object> params) {
		StreamSource ss =  apiCaller.getStreamSource(params);
		return ss;
	}
	
	public String naverApiToString(Map<String, Object> params) {
		String ss =  apiCaller.getString(params);
		return ss;
	}

	public List<Item> searchNaverApi(Map<String, Object> params) {
		StreamSource ss =  apiCaller.getStreamSource(params);
		ShopItems shopItems = (ShopItems) unmarshaller.unmarshal(ss);
		
		//캐싱 + 검색정보 저장(통계용)
		
		return shopItems.getChannel().getItem();
	}
	

}
