package appariement;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class Lancement {



    private final WebClient.Builder webClientBuilder;
    private ArrayList<WebClient> parties = new ArrayList<>();

    public Lancement(WebClient.Builder builder) {
        this.webClientBuilder = builder;
    }




    public void rechercherJoueur(String url) {
        if (parties.size() >= 1) {
            WebClient webClient = parties.get(0);
            webClient.post().uri("/rechercherJoueur")
                    .body(Mono.just(url), String.class)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        }
    }

    public void demarrer() {
        if (parties.size() >= 1) {
            WebClient webClient = parties.get(0); // choix de la partie à contacter à implémenter
            webClient.post().uri("/demarrer")
                    .retrieve()
                    .bodyToMono(Void.class)// aucun retour donc void
                    .block();
        }
    }

    public void setAddUrlPartie(String urlPartie) {
        parties.add(webClientBuilder.baseUrl(urlPartie).build());
    }
}