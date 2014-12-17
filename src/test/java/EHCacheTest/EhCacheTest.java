package ehCacheTest;
import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.support.EHCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import core.config.ApplicationConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
public class EhCacheTest {

	@Autowired
	CacheManager ehCache;
	
	@Autowired
	Cache cache;
	
	@Autowired
	EHCacheService ehCacheService;
	
	@Test
	public void cacheManagerNotNullTest() {
		assertThat(ehCache, is(notNullValue()));
	}
	
	@Test
	public void cacheNotNullTest() {
		assertThat(cache, is(notNullValue()));
	}
	
	@Test
	public void cachePutAndGetTest() {
		Element element = new Element("key01", "Value01");
		cache.put(element);
		assertThat("Value01", is(cache.get("key01").getValue()));
	}
	
	@Test
	public void ehCacheServiceTest() {
		String query = "청바지";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("display", 10);
		params.put("start",1);
		params.put("query", query);
		params.put("sort", "sim");
		ehCacheService.getResponse(params);
	}
}
