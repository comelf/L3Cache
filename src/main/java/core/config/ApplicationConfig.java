package core.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.apache.commons.dbcp.BasicDataSource;
import org.l3cache.dao.ShopItems;
import org.l3cache.dao.UserDao;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import core.search.ApiCaller;
import core.search.EHCacheService;
import core.search.SearchHelper;

@Configuration
@PropertySource(value = "classpath:application-properties.xml")
@ComponentScan(basePackages = "org.l3cache")
//@MapperScan(basePackages="org.l3cache.model")
public class ApplicationConfig {
	@Resource
	private Environment env;

	@Autowired
	private ApplicationContext ac;
	
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
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env
				.getRequiredProperty("database.driverClassName"));
		ds.setUrl(env.getRequiredProperty("database.url"));
		ds.setUsername(env.getRequiredProperty("database.username"));
		ds.setPassword(env.getRequiredProperty("database.password"));
		return ds;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		factoryBean.setTypeAliasesPackage("org.l3cache.model");
		factoryBean.setMapperLocations(ac.getResources("classpath:org/l3cache/dao/UserMapper.xml"));
		
		return factoryBean;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    resolver.setMaxUploadSize(10000);
	    return resolver;
	}
	
	
}
