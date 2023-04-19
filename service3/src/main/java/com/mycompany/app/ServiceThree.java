package com.mycompany.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceThree {

    private static final Logger logger = LogManager.getLogger(ServiceThree.class);

    public static void main(String[] args) {

        SpringApplication.run(ServiceThree.class, args);

        logger.info("Service 3 started. Lessgo ! ");

    }

}