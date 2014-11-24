package core.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.l3cache.app.PostManager;
import org.l3cache.dao.ShopItems;
import org.l3cache.init.InitDatabase;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import core.search.ApiCaller;
import core.search.EHCacheService;
import core.search.SearchHelper;
import core.utils.FileManager;

@Configuration
@PropertySource(value = "classpath:application-properties.xml")
@ComponentScan(basePackages = "org.l3cache")
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
	
//	@Bean
//	public DataSource dataSource() {
//		BasicDataSource ds = new BasicDataSource();
//		ds.setDriverClassName(env
//				.getRequiredProperty("database.driverClassName"));
//		ds.setUrl(env.getRequiredProperty("database.url"));
//		ds.setUsername(env.getRequiredProperty("database.username"));
//		ds.setPassword(env.getRequiredProperty("database.password"));
//		return ds;
//	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env
				.getRequiredProperty("mysql.driverClassName"));
		ds.setUrl(env.getRequiredProperty("mysql.url"));
		ds.setUsername(env.getRequiredProperty("mysql.username"));
		ds.setPassword(env.getRequiredProperty("mysql.password"));
		
		return ds;
	}
	
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return factoryBean.getObject();
	}

	@Bean
	public SqlSession sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setDefaultEncoding("utf-8");
	    multipartResolver.setMaxUploadSize(100000);
	    return multipartResolver;
	}
	
	@Bean
	public PostManager postManager() throws Exception {
		return new PostManager(sqlSession());
	}
	
	@Bean
	public FileManager fileManager() {
		return new FileManager();
	}
	
	@Bean
	public InitDatabase initDatabase() {
		return new InitDatabase();
	}
	
}
