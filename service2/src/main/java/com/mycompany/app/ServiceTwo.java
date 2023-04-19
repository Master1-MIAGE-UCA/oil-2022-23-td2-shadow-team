package com.mycompany.app;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceTwo {

    private static final Logger logger = LogManager.getLogger(ServiceTwo.class);

    public static void main(String[] args) {

        SpringApplication.run(ServiceTwo.class, args);

        logger.info("Service 2 started. Lessgo ! ");

    }

}