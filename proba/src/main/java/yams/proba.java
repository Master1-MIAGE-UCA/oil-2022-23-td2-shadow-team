package yams;

import appariement.appariement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class proba {

    private static final Logger logger = LogManager.getLogger(proba.class);
    public static void main(String[] args) {
        SpringApplication.run(proba.class, args);
        appariement.main(args);
        logger.info("Proba started. Lessgo ! ");
    }
}