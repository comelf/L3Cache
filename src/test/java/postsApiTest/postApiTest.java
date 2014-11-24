package postsApiTest;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.app.PostManager;
import org.l3cache.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import core.config.ApplicationConfig;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
public class postApiTest {

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	PostManager postManager;
	
	@Test
	public void postsListTest() {
		int start = 1;
		
		List<Post> list = postManager.getRecentlyLists(start);
		
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
		
		Post newPost = new Post(title,shopUrl,contents,imgUrl,price,writer);
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