package core.config;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.l3cache.dto.ShopItems;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import core.search.ApiCaller;
import core.search.EHCacheService;
import core.search.SearchHelper;

@Configuration
@PropertySource(value = "classpath:api-properties.xml")
public class ApplicationConfig {
	@Resource
	private Environment env;

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean(name="naverApiCaller")
	public ApiCaller naverApiCaller(){
		ApiCaller call = new ApiCaller(restTemplate());
		call.setApiKey(env.getRequiredProperty("naver.shop.key"));
		call.setApiUrl(env.getRequiredProperty("naver.shop.url"));
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
	
	@Bean
	public CacheManager cacheManager(){
		return CacheManager.create();
	}
	
	@Bean
	public Cache cache(){
		return cacheManager().getCache("naverApiCache");
	}
	 
	@Bean
	public EHCacheService ehCacheService(){
		return new EHCacheService(searchHelper(),cache());
	}
}
