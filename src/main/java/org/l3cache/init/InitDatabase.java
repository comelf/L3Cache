package org.l3cache.init;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class InitDatabase {

	private static final Logger log = LoggerFactory
			.getLogger(InitDatabase.class);
	
	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	public void initialize(){
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("l3cache.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
		log.info("database initialized success!");
	}
}
