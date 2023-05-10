package yams;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.InetAddress;

@SpringBootApplication
public class JoueurApplication {

    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        // les traces sont là juste pour montrer le déroulement et le lancement
        SpringApplication.run(JoueurApplication.class, args);
    }

    @Bean
    public CommandLineRunner scriptLancement(JoueurConcret joueur, WebClient.Builder builder) {
        return args -> {
            String name = "nom par defaut";
            if (args.length > 0) name = args[0];
            joueur.setName(name);
            if (args.length > 1) {
                // args[1] c'est l'uril d'appariement, en version courte ici pour simplifier la ligne de commande
                String urlApp = "http://localhost:"+args[1];
                WebClient clientApp = builder.baseUrl(urlApp).build();

                String myIp = InetAddress.getLocalHost().getHostAddress();
                String myUrl = "http://"+myIp+":"+port;

                String urlPartie = clientApp.post().uri("/ajouterJoueur")
                        .body(Mono.just(myUrl), String.class)
                        .retrieve().bodyToMono(String.class)
                        .block();

                if ((urlPartie != null) && (urlPartie != "")) {
                    System.out.println(name+"> je dois me signaler");
                    WebClient clientPartie = builder.baseUrl(urlPartie).build();
                    clientPartie.post().uri("/rechercherJoueur")
                            .body(Mono.just(myUrl), String.class)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(Void.class)
                            .block();

                    // le dernier a se connecter lance la partie
                    if (args.length > 2) {
                        System.out.println(name+"> je lance la partie");
                        clientPartie.post().uri("/demarrer")
                                .retrieve()
                                .bodyToMono(Void.class)
                                .block();
                    }
                }

            }
            // pas de fin car il n'y a un webserveur de lancer
        };
    }

}