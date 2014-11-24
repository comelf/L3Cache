package org.l3cache.init;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Resource
public class InitContext {

	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	public void initialize(){
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("l3cache.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
	}
}
