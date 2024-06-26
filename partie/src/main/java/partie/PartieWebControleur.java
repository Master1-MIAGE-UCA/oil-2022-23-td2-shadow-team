package partie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import commun.YamsPlayer;

@RestController
public class PartieWebControleur {

    Object synchro = new Object();

    int nbRequetes = 0;
    int nbJoueur = 0;


    @Autowired
    Partie partie;

    private final WebClient.Builder webClientBuilder;

    public PartieWebControleur(WebClient.Builder builder) {
        this.webClientBuilder = builder;
    }

    @PostMapping("/rechercherJoueur")
    public void rechercheJoueur(@RequestBody String url) {
        System.out.println("partie> recherche de  "+ url);
        synchronized (synchro) {
            nbRequetes++;
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                YamsPlayer player = new PlayerProxy(webClientBuilder.baseUrl(url).build());
                System.out.println("partie > ajout de "+player.getName()+" / "+url);
                partie.addPlayer(player);
                synchronized (synchro) {
                    nbJoueur++;
                    synchro.notify();
                }
            }
        });
        t.start();
    }

    @PostMapping("/demarrer")
    public void demarrer() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (synchro) {
                    while (nbJoueur < nbRequetes) {
                        try {
                            System.out.println("partie> attente d'un joueur...");
                            synchro.wait();
                            System.out.println("partie> un joueur de plus...");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                partie.demarreGame();
            }
        });
        t.start();
    }



}
