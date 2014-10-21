package core.config;

import org.l3cache.dto.ShopItems;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import core.search.ApiCaller;
import core.search.SearchHelper;

@Configuration
public class ApplicationConfig {
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean(name="naverApiCaller")
	public ApiCaller naverApiCaller(){
		ApiCaller call = new ApiCaller(restTemplate());
		call.setApiKey("77944ea87424da7ad1d8ff35b4e8423a");
		call.setApiUrl("http://openapi.naver.com/search?key={key}&query={query}&display={display}&start={start}&target=shop&sort={sort}");
		return call;
	}
	
	@Bean
	public SearchHelper searchHelper(){
		return new SearchHelper(naverApiCaller(), jaxb2Marshaller());
	}
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller(){
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(ShopItems.class);
		return jaxb2Marshaller;
	}
	 
}
