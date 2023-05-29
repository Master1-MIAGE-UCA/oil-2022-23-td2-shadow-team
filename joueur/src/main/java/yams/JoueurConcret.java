package yams;

import commun.*;
import org.springframework.stereotype.Component;


import java.security.SecureRandom;
import java.util.List;
import java.util.Random;


//La classe JoueurConcret représente un JoueurConcret dans le jeu de Yams. Dans cette implémentation particulière, le joueur choisit ses actions de manière aléatoire.

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
        System.out.println("Mes choix disponibles: " + MesChoixDisponibles);
        List<Integer> listeARelancer = coup.ListeARelancer();

        int nombreLancers = 0; // compte le premier lancer hors de la boucle
       //yams 3 lance en cas ou il y'as pas le choix
        while (nombreLancers < 3 && MesChoixDisponibles.size() == 0) {
            coup.relancer(listeARelancer);
            des = coup.getDes();
            MesChoixPoussibles = coup.getCategories();
            MesChoixDisponibles = this.scores.getCategoriesDisponibles(MesChoixPoussibles); // je filtre avec mes disponibile dans le tableau de score
            System.out.println("Mes choix disponibles: " + MesChoixDisponibles);
            nombreLancers++;
            listeARelancer.clear(); // vider la liste pour la prochaine relance
            listeARelancer = coup.ListeARelancer();
        }

        if (MesChoixDisponibles.size() == 0)
        {
            //int score = calculerScorePourCategorie(categorieChoisie, des);
            // Sélectionne une catégorie aléatoirement parmi les catégories non utilisées
            List<String>   categoriesNonUtilisees = this.scores.getCategoriesNonUtilisees();
            int indexAleatoire = new Random().nextInt(categoriesNonUtilisees.size());
            String categorieChoisie = categoriesNonUtilisees.get(indexAleatoire);
            this.scores.setScoreCategorie(categorieChoisie, 0);
            System.out.println(this.getName() + " a choisi la catégorie " + categorieChoisie + " avec un score de " + 0 + ".");
        }
        else {
            String categorieChoisie = MesChoixDisponibles.get(rand.nextInt(MesChoixDisponibles.size()));
            //int score = calculerScorePourCategorie(categorieChoisie, des);
            // Mettre à jour le score dans la catégorie choisie
            this.scores.setScoreCategorie(categorieChoisie, 10);
            //affiche de choix
            System.out.println(this.getName() + " a choisi la catégorie " + categorieChoisie + " avec un score de " + 10 + ".");
        }

        System.out.println("Grille de Score :");
        System.out.println(this.scores.affichagetableauScore());

        return coup;
    }






}