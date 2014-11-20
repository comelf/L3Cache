package databaseTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.dao.UserDao;
import org.l3cache.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import core.config.ApplicationConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
public class UserDaoTest {
//
//	@Autowired
//	private SqlSession sqlSession;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void gettingStarted() {
		SqlSession session = sqlSessionFactory.openSession();
		
		User user = new User(3, "hihi", "pwpw");
		session.selectOne("UserMapper.create", user);
		
		User result = session.selectOne("UserMapper.findById", "hihi");
		
		assertThat(result.getPassword(), is("pwpw"));

	}
	


}
