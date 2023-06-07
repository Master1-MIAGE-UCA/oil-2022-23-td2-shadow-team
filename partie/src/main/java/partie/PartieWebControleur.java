package partie;


import commun.IJoueur;
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
    public void jouer(@RequestBody String url) {
        System.out.println("partie> recherche de  "+ url);
        synchronized (synchro) {
            nbRequetes++;
        }
        // java.lang.IllegalStateException: block()/blockFirst()/blockLast() are blocking, which is not supported in thread reactor-http-nio-3
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                IJoueur j = new JoueurProxy( webClientBuilder.baseUrl(url).build());
                YamsPlayer player = new PlayerProxy(webClientBuilder.baseUrl(url).build());
                System.out.println("partie > ajout de "+player.getName()+" / "+url);
//                partie.addJoueur(j);
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
