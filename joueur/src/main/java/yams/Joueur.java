package yams;

import commun.Coup;
import commun.EtatDuJeu;
import commun.IJoueur;


import java.security.SecureRandom;
import java.util.Random;

public class Joueur extends IJoueur {
    Random rand = new SecureRandom();


    public Joueur(String name) {
        super();
        setName(name);
    }

    public Coup jouer(EtatDuJeu etat) {
        int val = rand.nextInt(100);
        System.out.println(getName() + "> je joue " + val);
        return new Coup("valeur aléatoire " + val, this);
    }

    public static void main(String[] args) {
        Joueur j = new Joueur("joueur1");
        System.out.println(j.getName());
    }
}