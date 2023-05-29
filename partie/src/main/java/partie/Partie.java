package partie;


import commun.Coup;
import commun.EtatDuJeu;
import commun.IJoueur;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;

@Component
public class Partie {

    EtatDuJeu etat = new EtatDuJeu();
    ArrayList<IJoueur> joueurs = new ArrayList<>();

    public void addJoueur(IJoueur j) {
        joueurs.add(j);
    }

    /**
     * code à mieux structurer, ne pas mettre les sout ici (séparer vue / modèle)
     * passer plus de paramètre pour jouer, etc.
     */
    public void demarrer() {

        System.out.println("partie> début de la partie");
        while (!etat.isFini()) {
            System.out.println("partie> début de la manche " + etat.getNbTours());
            for (IJoueur j : joueurs) {
                System.out.println("partie> c'est le tour de " + j.getName());
                Coup c = j.jouer(etat);
                // vérifier et résoudre et afficher le coup
                // on pourrait vérifier que le joueur de c a le même nom que le joueur de j
                System.out.println("partie> " + c);
                System.out.println("partie> fin du tour de " + j.getName());
            }
            System.out.println("partie> fin de la manche " + etat.getNbTours());
            etat.setNbTours(etat.getNbTours() + 1);
        }
        System.out.println("========================================================================");
        System.out.println("Score Final : ");
        int maxScore = -1;
        List<IJoueur> winners = new ArrayList<>();
        for (IJoueur j : joueurs) {
            System.out.println(j.getName() + " : ");
            System.out.println(j.getTableauScore());
            int score = j.getScore();
            if (score > maxScore) {
                maxScore = score;
                winners.clear();
                winners.add(j);
            } else if (score == maxScore) {
                winners.add(j);
            }
        }
        if (winners.size() > 1) {
            System.out.println("Il y a un match nul entre les joueurs suivants avec un score de " + maxScore + " :");
            for (IJoueur j : winners) {
                System.out.println(j.getName());
            }
        } else if (winners.size() == 1) {
            System.out.println("Le gagnant est " + winners.get(0).getName() + " avec un score de " + maxScore);
        }

        System.out.println("partie> fin de la partie");

    }
}