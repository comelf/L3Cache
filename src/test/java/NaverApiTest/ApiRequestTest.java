package NaverApiTest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.dto.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.config.ApplicationConfig;
import core.search.SearchHelper;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;

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
	public void xmlMarsallingTest() throws XmlMappingException, IOException, JAXBException {
		String query = "청바지";
		long startTime = System.currentTimeMillis();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("display", 10);
		params.put("start",1);
		params.put("query", query);
		params.put("sort", "sim");
		
		List<Item> list = searchHelper.searchNaverApi(params);
		
		long endTime = System.currentTimeMillis();
		log.debug("test query = '{}', time ={}" , query,((endTime - startTime )/1000.0)); 
		ObjectMapper mapper = new ObjectMapper();
		log.info(mapper.writeValueAsString(list));
		//assertThat(list.,is());
	}
	
}
