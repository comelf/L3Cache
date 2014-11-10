package core.search;

import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.l3cache.dto.ShopItems;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class SearchHelper {
	private ApiCaller apiCaller;
	private Jaxb2Marshaller unmarshaller;
	
	public SearchHelper(ApiCaller apiCaller, Jaxb2Marshaller jaxb2Marshaller) {
		this.apiCaller = apiCaller;
		this.unmarshaller = jaxb2Marshaller;
	}

	//test method
	public StreamSource naverApiStream(Map<String, Object> params) {
		StreamSource ss =  apiCaller.getStreamSource(params);
		return ss;
	}
	
	//test method
	public String naverApiToString(Map<String, Object> params) {
		String ss =  apiCaller.getString(params);
		return ss;
	}
	
	//apiCaller가 넘기는 값에 대한 에러 처리해야됨.
	// 예) 결과값이 없을경우, 일일 쿼리 제한을 넘길경우
	// return null로 넘기지 말고 에러에 대한것 넘길것!
	public ShopItems searchNaverApi(Map<String, Object> params) {
		StreamSource ss =  apiCaller.getStreamSource(params);
		ShopItems shopItems = (ShopItems) unmarshaller.unmarshal(ss);
		
		if(shopItems.getChannel().getTotal()==0)
			return null;

		return shopItems;
	}
	

}
