package com.mycompany.app.oil202223td2shadowteam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oil202223Td2ShadowTeamApplication {

	private static final Logger logger = LogManager.getLogger(Oil202223Td2ShadowTeamApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(Oil202223Td2ShadowTeamApplication.class, args);

		logger.info("Application started. Lessgo ! ");

	}

}
