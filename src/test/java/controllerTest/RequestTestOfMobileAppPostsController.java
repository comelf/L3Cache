package controllerTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.mobileController.MobilePostsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import core.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
public class RequestTestOfMobileAppPostsController {

	private MockMvc mockMvc;
	
	@Autowired
	MobilePostsController mobileAppPostsControllerMock;
	

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(mobileAppPostsControllerMock)
				.setMessageConverters(new MappingJackson2HttpMessageConverter(), new StringHttpMessageConverter()).build();
	}
	
	
	@Test
	public void test2() throws Exception {
		//when(methodCall)
		
//		this.mockMvc.perform(
//						get("/app/posts/")
//							.accept(MediaType.parseMediaType("application/json"))
//							.param("id", "1")
//							.param("start", "1")
//							.param("sort", "0"))
//					.andExpect(status().isOk())
//					.andExpect(model().attributeExists("response"))
//					.andExpect(model().attribute("response", hasProperty("result", is(10))))
//					.andDo(MockMvcResultHandlers.print());
		
	}
	


}
