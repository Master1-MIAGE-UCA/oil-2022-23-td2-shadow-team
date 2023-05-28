package yams;

import commun.*;
import org.springframework.stereotype.Component;


import java.security.SecureRandom;
import java.util.List;
import java.util.Random;




@Component
public class JoueurConcret extends IJoueur {
    Random rand = new SecureRandom();
    private ScoreJoueur scores; // definire une map pour le score



    public JoueurConcret() {
        setName("sans nom");
        this.scores = new ScoreJoueur(); // Initialiser le score pour chaque joueur

    }

/*
    public Coup jouer(EtatDuJeu etat) {
        int val = rand.nextInt(100);
        System.out.println(getName()+"> je joue "+val);
        return new Coup("valeur aléatoire "+val, this);
    }


 */


    public int getScore() {
        return scores.getTotalScore();
    }

    public void setScoreCategorie(String categorie, int score) {
        scores.setScoreCategorie(categorie, score);
    }
    public ScoreJoueur getVariableScrore()
    {
        return this.scores;
    }



    public Coup jouer(EtatDuJeu etat) {
        Coup coup = new Coup("lancer", this);
        coup.lancer();
        List<Integer> des=coup.getDes();
        List<String> MesChoixPoussibles = coup.getCategories();
        List<String> MesChoixDisponibles = this.scores.getCategoriesDisponibles(MesChoixPoussibles);// je filtre avec mes disponibile dans le tableau de score
        List<Integer> listeARelancer = coup.ListeARelancer();
        int nombreLancers = 1; // compte le premier lancer hors de la boucle
       //yams 3 lance en cas ou il y'as pas le choix
        while (nombreLancers < 3 && MesChoixDisponibles.size() == 0) {
            coup.relancer(listeARelancer);
            des = coup.getDes();
            MesChoixPoussibles = coup.getCategories();
            MesChoixDisponibles = this.scores.getCategoriesDisponibles(MesChoixPoussibles); // je filtre avec mes disponibile dans le tableau de score
            nombreLancers++;
            listeARelancer.clear(); // vider la liste pour la prochaine relance
            listeARelancer = coup.ListeARelancer();
        }
        if (MesChoixDisponibles.size() == 0)
        {

        }
        else {

        }


        return coup;
    }






}