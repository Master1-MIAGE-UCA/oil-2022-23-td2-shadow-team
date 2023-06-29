package appariement;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Appariement {

    /// appariement ne fait plus de requete... plus besoin de webclient ni de builder (en tout cas en l'état)

    private final ArrayList<String> urlParties = new ArrayList<>();
    private final ArrayList<String> urlPlayers = new ArrayList<>();

    public Appariement() {
    }

    public String aadPlayer(String urlPlayer){
        this.urlPlayers.add(urlPlayer);
        if (this.urlParties.size() > 0 ) return this.urlParties.get(0);
        return "";
    }


    public String[] addPartie(String partieUrl) {
        urlParties.add(partieUrl);
        if (urlPlayers.size() > 0) return urlPlayers.toArray(new String[urlPlayers.size()]); // ici gestion des joueurs à améliorer
        // ne pas renvoyer un joueur qui fait 2 partie en meme temps
        else return null;
    }
}
