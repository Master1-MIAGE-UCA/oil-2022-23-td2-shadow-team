package partie;

import hebergeur.hebergeur;
import appariement.appariement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Partie {

    private static final Logger logger = LogManager.getLogger(Partie.class);

    public static void main(String[] args) {

        SpringApplication.run(Partie.class, args);
        appariement.main(args);
        hebergeur.main(args);
        logger.info("Application started. Lessgo ! ");

    }
}
