package yams;

import commun.Calculator;
import commun.Coup;
import commun.EtatDuJeu;
import commun.IJoueur;
import org.springframework.stereotype.Component;


import java.security.SecureRandom;
import java.util.Random;

@Component
public class JoueurConcret extends IJoueur {
    Random rand = new SecureRandom();


    public JoueurConcret() {
        setName("sans nom");
    }


    public Coup jouer(EtatDuJeu etat) {
        int val = rand.nextInt(100);
        System.out.println(getName()+"> je joue "+val);
        return new Coup("Coup aléatoire "+val, this);
    }

}