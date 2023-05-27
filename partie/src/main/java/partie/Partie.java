package partie;


import commun.Coup;
import commun.EtatDuJeu;
import commun.IJoueur;
import org.springframework.stereotype.Component;

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
        while (! etat.isFini()) {
            System.out.println("partie> début de la manche "+etat.getNbTours());
            for(IJoueur j : joueurs) {
                System.out.println("partie> c'est le tour de "+j.getName());
                Coup c = j.jouer(etat);
                // vérifier et résoudre et afficher le coup
                // on pourrait vérifier que le joueur de c a le même nom que le joueur de j
                System.out.println("partie> "+c);
                System.out.println("partie> fin du tour de "+j.getName());
            }
            System.out.println("partie> fin de la manche "+etat.getNbTours());
            etat.setNbTours(etat.getNbTours()+1);
        }
        System.out.println("partie> fin de la partie");
    }
}
