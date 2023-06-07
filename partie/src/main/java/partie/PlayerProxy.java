package partie;

import commun.Coup;
import commun.EtatDuJeu;
import commun.EtatJeuService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import commun.YamsPlayer;
import reactor.core.publisher.Mono;

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

    @Override
    public String play(EtatJeuService etatJeuService){
        return webClient.post().uri("/jouer")
                .body(Mono.just(etatJeuService), EtatJeuService.class) // on envoie le corps/parametre de la requete sous forme d'objet EtatDuJeu
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public WebClient getWebClient() {
        return webClient;
    }

    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }
}
