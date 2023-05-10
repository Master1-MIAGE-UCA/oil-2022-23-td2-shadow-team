package appariement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppariementApplication {
    public static void main(String[] args) {
        // les traces sont là juste pour montrer le déroulement et le lancement
        SpringApplication.run(AppariementApplication.class, args);
    }

    @Bean
    public CommandLineRunner scriptLancement(Appariement lancement) {
        return args -> {
            // pas de fin car il n'y a un webserveur de lancer


            // ce n'est plus ici, ce sont joueurs et parties qui se "signalent" --> d'où une API WEB

            /*
            if (args.length >= 1) {
                // 1er args : l'url de partie
                String urlPartie = "http://localhost:"+args[0];
                lancement.setAddUrlPartie(urlPartie);

                // les autres args : les urls des joueurs
                if (args.length >= 2) {
                    for(int i = 1; i < args.length; i++) {
                        String port = args[i];
                        String url = "http://localhost:"+port;
                        lancement.rechercherJoueur(url);

                    }
                    lancement.demarrer();
                }



            }

             */

        };
    }

}
