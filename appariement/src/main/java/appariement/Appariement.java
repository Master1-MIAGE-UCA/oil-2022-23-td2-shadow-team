package appariement;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Appariement {
    public static void main(String[] args) {
        // les traces sont là juste pour montrer le déroulement et le lancement
        SpringApplication.run(Appariement.class, args);
    }

    @Bean
    public CommandLineRunner scriptLancement(Lancement lancement) {
        return args -> {
            lancement.demarrer();
            // pas de fin car il n'y a un webserveur de lancer
        };
    }


}
