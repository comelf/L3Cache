package databaseTest;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.l3cache.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
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
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	private DataSource dataSource;
	
//	@Test
//	public void gettingStarted() {
//		SqlSession session = sqlSessionFactory.openSession();
//		
//		User user = new User("testUser@test.com", "test");
//		session.selectOne("UserMapper.create", user);
//		
//		User result = session.selectOne("UserMapper.findByEmail", "testUser@test.com");
//		
//		assertThat(result.getPassword(), is("test"));
//		session.close();
//	}
	
	@Test
	public void nullTest() {
		SqlSession session = sqlSessionFactory.openSession();

		User result = session.selectOne("UserMapper.findByEmail", "qwerqwer@test.com");
		
		assertThat(result.getPassword(), is("test"));
		session.close();
	}

}
