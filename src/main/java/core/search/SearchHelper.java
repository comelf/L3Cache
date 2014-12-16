package core.search;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.l3cache.dao.AdultResult;
import org.l3cache.dao.ShopItems;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class SearchHelper {
	private static final int ADULT_QUERY = 1;
	private ApiCaller apiCaller;
	private Jaxb2Marshaller unmarshaller;
	
	public SearchHelper(ApiCaller apiCaller, Jaxb2Marshaller jaxb2Marshaller) {
		this.apiCaller = apiCaller;
		this.unmarshaller = jaxb2Marshaller;
	}
	
	public ShopItems searchNaverApi(Map<String, Object> params) {
		StreamSource ss =  apiCaller.getShopApiStreamSource(params);	
		ShopItems shopItems = null;
		
		try {
			shopItems = (ShopItems) unmarshaller.unmarshal(ss);
		} catch (Exception e) {
			shopItems = ShopItems.emptyItems();
		}
		
		return shopItems;
	}

	public boolean isAdultQuery(Map<String, Object> params) {
		StreamSource ss = apiCaller.getAdultApiStreamSource(params);
		AdultResult adultResult = (AdultResult) unmarshaller.unmarshal(ss);
		if(adultResult.getItem().getAdult()==ADULT_QUERY){
			return true;
		}else{
			return false;
		}
	}

	public StreamSource naverApiStream(Map<String, Object> params) {
		return apiCaller.getShopApiStreamSource(params);
	}
	
	public void printSource(StreamSource ss){
		try{
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.transform(ss,result);
			String strResult = writer.toString();
			System.out.println(strResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
