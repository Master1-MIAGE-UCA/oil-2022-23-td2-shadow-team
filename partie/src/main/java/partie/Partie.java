package partie;


import commun.*;
import commun.constants.TypeCombinaison;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.*;

@Component
public class Partie {
    private final EtatJeuService etatJeuService = new EtatJeuService();
    ArrayList<YamsPlayer> players = new ArrayList<>();
    ArrayList<Map<TypeCombinaison, FeuilleYams>> feuilleDesJoueurs = new ArrayList<>();
    private FeuilleYams feuilleYams;

    public void addPlayer(YamsPlayer player) {
        this.players.add(player);
    }

    /**
     * code à mieux structurer, ne pas mettre les sout ici (séparer vue / modèle)
     * passer plus de paramètre pour jouer, etc.
     */

    public void demarreGame() {
        while (!this.etatJeuService.estNombreDeTourFini()) {
            if (this.players != null && !CollectionUtils.isEmpty(this.players)) {

                for (YamsPlayer player : this.players) {
                    player.play(etatJeuService);
//                    this.affichageDesStatistiques(player);
                }
            }
            this.etatJeuService.incrementeNombreDeTour();
        }

    }

    public void affichageDesStatistiques(YamsPlayer player) {
        System.out.println("=================================================");
        System.out.println("Statistiques de la partie Yams");
        if (player != null) {
            System.out.println("=================================================");
            System.out.println("Points marques par le joueur " + player.getName());
            System.out.println("=================================================");
            for (Map.Entry<commun.constants.TypeCombinaison, FeuilleYams> entry : player.getFeuilleYamsAR().entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue().getScore() + " " + entry.getValue().estCaseScoreBarre());
            }
        }
        Map<String, Integer> winners = new HashMap<>();
        if (player != null) {
            List<Integer> scores = new ArrayList<>();
            for (Map.Entry<TypeCombinaison, FeuilleYams> entry : player.getFeuilleYamsAR().entrySet()) {
                scores.add(entry.getValue().getScore());
            }
            winners.put(player.getName(), scores.stream().mapToInt(Integer::intValue).sum());
        }
        System.out.println("=================================================");

        winners.forEach((s, integer) -> System.out.println(s + " a marqué " + integer + " points le jeu"));

        System.out.println("=================================================");
    }


}
