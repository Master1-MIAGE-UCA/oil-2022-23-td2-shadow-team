package com.mycompany.app;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceOne {

    private static final Logger logger = LogManager.getLogger(ServiceOne.class);

    public static void main(String[] args) {

        SpringApplication.run(ServiceOne.class, args);

        logger.info("Service 1 started. Lessgo ! ");

    }

}
