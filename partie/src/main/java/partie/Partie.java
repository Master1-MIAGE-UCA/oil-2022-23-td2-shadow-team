package partie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class Partie {

    private static final Logger logger = LogManager.getLogger(Partie.class);

    public static void main(String[] args) {
        SpringApplication.run(Partie.class, args);
        logger.info("Application started. Lessgo ! ");
    }
}
