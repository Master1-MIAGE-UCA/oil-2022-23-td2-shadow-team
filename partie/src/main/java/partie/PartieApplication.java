package partie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.InetAddress;

@SpringBootApplication
public class PartieApplication {
    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        // les traces sont là juste pour montrer le déroulement et le lancement
        SpringApplication.run(PartieApplication.class, args);
    }


    @Bean
    public CommandLineRunner scriptLancement( WebClient.Builder builder) {
        return args -> {
            if (args.length > 0) {
                // args[0] c'est l'uril d'appariement, en version courte ici pour simplifier la ligne de commande
                String urlApp = args[0];
                WebClient client = builder.baseUrl(urlApp).build();

                String myIp = InetAddress.getLocalHost().getHostAddress();
                String[] urJoueurs = client.post().uri("/ajouterPartie")
                        .body(Mono.just("http://" + myIp + ":" + port), String.class)
                        .retrieve().bodyToMono(String[].class).block();

                if ((urJoueurs != null) && (urJoueurs.length > 0)) {
                    System.out.println("partie > je me signale auprès de joueurs");
                }
            }
        };
    }
}