package controllerTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import util.RestAssuredUtil;

import com.jayway.restassured.response.Response;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import core.config.ApplicationConfig;
import static com.jayway.restassured.RestAssured.given;

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
		RestAssuredUtil.setup();
		//RestAssuredMockMvc.standaloneSetup(new MobilePostsController());
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
        	.statusCode(200)
        	.body("result", equalTo(10))
        	.body("total", equalTo(51)).body("data.pid[1]", equalTo(50));
		
		//System.out.println(response.body());
        	//.body("$.Response", equalTo(10));
        	//.body();
		
	}
	

}
