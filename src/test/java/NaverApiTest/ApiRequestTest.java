package naverApiTest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.dao.ShopItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import core.config.ApplicationConfig;
import core.search.SearchHelper;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
public class ApiRequestTest {
	@Autowired
	ApplicationContext ac;
	
	@Autowired
	SearchHelper searchHelper;
	
	private StreamSource shopApiStream;
	
	private static final Logger log = LoggerFactory.getLogger(ApiRequestTest.class);
	
	@Before
	public void apiStreamSource(){
		String query = "청바지";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("display", 10);
		params.put("start",1);
		params.put("query", query);
		params.put("sort", "sim");
		
		shopApiStream = searchHelper.naverApiStream(params);
	}
	
	@Test
	public void applicaionLoadTest() {
		assertNotNull(ac);
	}

	@Test
	public void apiRequestTest() {
		assertNotNull(shopApiStream);
	}
	
	@Test
	public void stringTest(){
		String query = "청바지";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("display", 10);
		params.put("start",1);
		params.put("query", query);
		params.put("sort", "sim");
		
		String result = searchHelper.naverApiToString(params);
		System.out.println(result);
	}
	
	@Test
	public void xmlMarsallingTest() throws XmlMappingException, IOException, JAXBException {
		String query = "청바지";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("display", 10);
		params.put("start",1);
		params.put("query", query);
		params.put("sort", "sim");
		
		ShopItems si = searchHelper.searchNaverApi(params);
		assertThat(si, is(notNullValue()));
	}
	
}
