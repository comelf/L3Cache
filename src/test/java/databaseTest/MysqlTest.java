package databaseTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import core.config.ApplicationConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationConfig.class, loader=AnnotationConfigContextLoader.class)
public class MysqlTest {

	@Autowired
	DataSource dataSource;
	
	@Test
	public void test() throws SQLException {
		
		assertNotNull(dataSource);
		
		Connection con = dataSource.getConnection();
		Statement s =  con.createStatement();
		boolean result = s.execute("select 1;");
		s.close();
		con.close();
		
		assertThat(result, is(true));
		
		
	}

}
