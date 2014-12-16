package postsApiTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.app.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import core.config.ApplicationConfig;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
public class PostApiDBTest {

	@Autowired
	PostManager postManager;
	
	@Test
	public void test() {
		
		postManager.readPost(2);
	}

}
