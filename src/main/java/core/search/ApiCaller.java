package core.search;

import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.springframework.web.client.RestTemplate;

public class ApiCaller {
	private static final int SUM_OF_PAGE = 5;
	private String apiKey;
	private String shopApiUrl;
	private String adultApiUrl;
	private RestTemplate restTemplate;

	public ApiCaller(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setShopApiUrl(String shopApiUrl) {
		this.shopApiUrl = shopApiUrl;
	}
	
	public void setAdultApiUrl(String adultApiUrl) {
		this.adultApiUrl = adultApiUrl;
	}

	public StreamSource getShopApiStreamSource(Map<String, Object> params) {
		int reqStart = (int)params.get("start");
		int apiStart = reqStart / SUM_OF_PAGE + reqStart % SUM_OF_PAGE;
		params.put("key", apiKey);
		params.put("start",apiStart);
		params.put("display",100);
		return restTemplate.getForObject(shopApiUrl, StreamSource.class, params);
	}

	public StreamSource getAdultApiStreamSource(Map<String, Object> params) {
		params.put("key", apiKey);
		params.put("target", "adult");
		return restTemplate.getForObject(adultApiUrl, StreamSource.class, params);
	}



}
