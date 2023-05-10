package appariement;

import commun.Coup;
import commun.EtatDuJeu;
import commun.IJoueur;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class JoueurProxy extends IJoueur {


    private WebClient webClient;

    public JoueurProxy(WebClient webClient) {
        setWebClient(webClient);
        String name = webClient.get().uri("/nom")// on fait une requete http get avec ce chemin
                .accept(MediaType.APPLICATION_JSON) // on utilise le format json pour la réponse
                .retrieve()// on execute la requete
                .bodyToMono(String.class) // on récupère le corps de la réponse sous forme de String
                .block(); // on fait une requete synchrone, on attends le resultat.
        setName(name);
    }

    @Override
    public Coup jouer(EtatDuJeu etat) {
        return webClient.post().uri("/jouer")
                .body(Mono.just(etat), EtatDuJeu.class) // on envoie le corps/parametre de la requete sous forme d'objet EtatDuJeu
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Coup.class)
                .block();
    }

    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public WebClient getWebClient() {
        return webClient;
    }
}
