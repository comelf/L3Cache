package postsApiTest;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.app.PostManager;
import org.l3cache.model.Post;
import org.l3cache.model.WritePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import core.config.ApplicationConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
public class PostApiTest {

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	PostManager postManager;
	
	@Autowired
	DataSource dataSource;
	
	@Before
	public void setUp() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("l3cache.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
	}
	
	@Test
	public void postsListTest() {
		int start = 1;
		
		List<Post> list = postManager.getRecentlyLists(start,1);
		
		assertThat(list, notNullValue());
		assertThat(list.size(), is(20));
	}
	
	@Test
	public void getRecentlyListsTest() {
		int start = 1;
		start = (start -1) *20;
		assertThat(start, is(0));
		List<Post> p = sqlSession.selectList("PostMapper.selectRecentlyList", start);
		
		assertThat(p.size(), is(20));
	}
	
	@Test
	public void insertTest() {
		
		String title ="test";
		String shopUrl = "test";
		String contents = "test";
		String imgUrl = "test.jpg";
		String price = "100";
		int writer = 1;
		
		WritePost newPost = new WritePost(title,shopUrl,contents,imgUrl,price,writer);
		int result = sqlSession.insert("PostMapper.create", newPost);
		
		assertThat(result, is(1));
	}
	
	
	@Test
	public void selectOnePostTest() {
		int pid = 3;
	
		
		Post result = postManager.getPostDetail(pid);
		
		
		assertThat(result.getTitle(), is("post 03"));
	}

}
