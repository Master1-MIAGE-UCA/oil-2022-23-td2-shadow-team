package commun;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EtatDuJeu {

    int nbTours = 1;
    boolean fini = false;



    private Map<IJoueur, ScoreJoueur> scores; // definire une map pour le score



    public int getNbTours() {
        return nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }

    public boolean isFini() {
        fini = (nbTours > 3);
        return fini;
    }

    public int getScore(IJoueur joueur) {
        ScoreJoueur scoreJoueur = scores.get(joueur);
        if (scoreJoueur == null) {
            return 0;
        }
        return scoreJoueur.getTotalScore();
    }

    public void setScoreCategorie(IJoueur joueur, String categorie, int score) {
        ScoreJoueur scoreJoueur = scores.get(joueur);
        if (scoreJoueur == null) {
            scoreJoueur = new ScoreJoueur();
            scores.put(joueur, scoreJoueur);
        }
        scoreJoueur.setScoreCategorie(categorie, score);
    }



    @Override
    public String toString() {
        return "EtatDuJeu{" + "nbTours=" + nbTours + ", fini=" + fini + '}';
    }




}
