package partie;

import commun.*;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class PlayerProxy extends YamsPlayer {
    private WebClient webClient;
    public PlayerProxy(WebClient webClient){
        this.webClient = webClient;
        String name = this.webClient.get().uri("/nom")// on fait une requete http get avec ce chemin
                .accept(MediaType.APPLICATION_JSON) // on utilise le format json pour la réponse
                .retrieve()// on execute la requete
                .bodyToMono(String.class) // on récupère le corps de la réponse sous forme de String
                .block();
        System.out.println("Received name "+name);
        this.setName(name);
    }

    public WebClient getWebClient() {
        return webClient;
    }

    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }
}
