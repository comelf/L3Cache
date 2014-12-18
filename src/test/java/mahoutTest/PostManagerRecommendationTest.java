package mahoutTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import util.RestAssuredUtil;
import core.config.ApplicationConfig;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
public class PostManagerRecommendationTest {
	
	@Before
	public void setup() throws Exception {
		RestAssuredUtil.setup();
	}
	
	@Test
	public void recommandTest() {
		given()
		.param("id", "1")
		.param("start", "1")
		.param("sort", "0").
    when()
    	.post("/app/posts/").
    then()
    	.statusCode(200)
    	.body("result", equalTo(10))
    	.body("total", equalTo(4))
    	.body("data.pid[1]", equalTo(12));
	}
	
	@Test
	public void recommandFailTest() {
		given()
		.param("id", "4")
		.param("start", "1")
		.param("sort", "0").
    when()
    	.post("/app/posts/").
    then()
    	.statusCode(200)
    	.body("result", equalTo(10))
    	.body("total", equalTo(0));
	}
	

}
