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
            lancement.preparerDemarrage();
            // pas de fin car il n'y a un webserveur de lancer
            if (args.length >= 1) {
                for(String port : args) {
                    String url = "http://localhost:"+port;
                    lancement.rechercherJoueur(url);

                }
                lancement.demarrer();
            }
            System.out.println("fin");
        };
    }


}
