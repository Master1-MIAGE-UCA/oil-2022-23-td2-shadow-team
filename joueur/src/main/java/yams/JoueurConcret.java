package yams;

import commun.Calculator;
import commun.Coup;
import commun.EtatDuJeu;
import commun.IJoueur;
import org.springframework.stereotype.Component;


import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Component
public class JoueurConcret extends IJoueur {
    Random rand = new SecureRandom();


    public JoueurConcret() {
        setName("sans nom");
    }

/*
    public Coup jouer(EtatDuJeu etat) {
        int val = rand.nextInt(100);
        System.out.println(getName()+"> je joue "+val);
        return new Coup("valeur aléatoire "+val, this);
    }


 */


    public Coup jouer(EtatDuJeu etat) {
        Coup coup = new Coup("lancer", this);
        coup.lancer();
        List<Integer> des=coup.getDes();
        List<String> MesChoixPoussibles = coup.getCategories();




        return coup;
    }



}