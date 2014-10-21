package core.search;

import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.springframework.web.client.RestTemplate;

public class ApiCaller {
	private String apiKey;
	private String apiUrl;
	private RestTemplate restTemplate;

	public ApiCaller(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	public StreamSource getStreamSource(Map<String, Object> params) {
		params.put("key", apiKey);
		return restTemplate.getForObject(apiUrl, StreamSource.class, params);
	}
	
	//test용
	public String getString(Map<String, Object> params) {
		params.put("key", apiKey);
		return restTemplate.getForObject(apiUrl, String.class, params);
	}

}
