package databaseTest;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import core.config.ApplicationConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
@DirtiesContext
public class UserDaoTest {

	@Autowired
	SqlSession sqlSession;

	
	@Test
	public void matchTest() {
		String userID = "haha@haha.com";
		String password = "hahahaha";
		
		User result = sqlSession.selectOne("UserMapper.findByEmail", userID);
		String matchPass = sqlSession.selectOne("UserMapper.findPassword", password);
		
		assertThat(result.getPassword(), is(matchPass));
	}

}
