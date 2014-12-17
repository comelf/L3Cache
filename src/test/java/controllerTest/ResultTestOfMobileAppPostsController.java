package controllerTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.mobileController.MobilePostsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import core.config.ApplicationConfig;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
public class ResultTestOfMobileAppPostsController {

//	//private MockMvc mockMvc;
//	
//	@Autowired
//	MobileAppPostsController mobileAppPostsControllerMock;
//	

	@Before
	public void setup() throws Exception {
		RestAssuredMockMvc.standaloneSetup(new MobilePostsController());
	}
	
	
	@Test
	public void test2() throws Exception {
		//when(methodCall)
		
		given()
			.param("id", "1")
			.param("start", "1")
			.param("sort", "0").
        when()
        	.post("/app/posts/").
        then()
        	.statusCode(200);
        	//.body("id", equalTo(1));
		
	}
	

}
