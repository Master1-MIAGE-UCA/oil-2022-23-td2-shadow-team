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
    ArrayList<Map<TypeCombinaison, CaseYams>> feuilleDesJoueurs = new ArrayList<>();
    private CaseYams caseYams;

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
            for (Map.Entry<commun.constants.TypeCombinaison, CaseYams> entry : player.getCaseYamsAR().entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue().getScore() + " " + entry.getValue().estCaseScoreBarre());
            }
        }
        Map<String, Integer> winners = new HashMap<>();
        if (player != null) {
            List<Integer> scores = new ArrayList<>();
            for (Map.Entry<TypeCombinaison, CaseYams> entry : player.getCaseYamsAR().entrySet()) {
                scores.add(entry.getValue().getScore());
            }
            winners.put(player.getName(), scores.stream().mapToInt(Integer::intValue).sum());
        }
        System.out.println("=================================================");

        winners.forEach((s, integer) -> System.out.println(s + " a marqué " + integer + " points le jeu"));

        System.out.println("=================================================");
    }

    private void initFeuilleYams(){
        CaseYams caseYams1 = new CaseYams();
        for (int i = 0; i < this.players.size(); i++){
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.YAMS, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.BRELAN, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.CARRE, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.PETITE_SUITE, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.GRANDE_SUITE, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.CHANCE, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.FULL, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.TOTAL_1, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.TOTAL_2, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.TOTAL_3, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.TOTAL_4, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.TOTAL_5, caseYams1);
            this.feuilleDesJoueurs.get(i).put(TypeCombinaison.TOTAL_6, caseYams1);
        }
    }

}
