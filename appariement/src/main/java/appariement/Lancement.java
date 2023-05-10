package appariement;


import commun.IJoueur;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import partie.Partie;

@Component
public class Lancement {


    private final WebClient.Builder webClientBuilder;

    public Lancement(WebClient.Builder builder) {
        this.webClientBuilder = builder;
    }

    Partie p ;
    public void preparerDemarrage() {
        p = new Partie();

    }

    public void rechercherJoueur(String url) {
        IJoueur j = new JoueurProxy(webClientBuilder.baseUrl(url).build());
        p.addIJoueur(j);
    }

    public void demarrer() {
        p.demarrer();
    }
}