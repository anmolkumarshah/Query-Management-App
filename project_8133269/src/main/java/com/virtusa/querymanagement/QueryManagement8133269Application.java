package com.virtusa.querymanagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class QueryManagement8133269Application {

	static Logger logger = LogManager.getLogger(QueryManagement8133269Application.class); 
	
	public static void main(String[] args) {	
		SpringApplication.run(QueryManagement8133269Application.class, args);
		logger.info("Application Started");
	}

}
