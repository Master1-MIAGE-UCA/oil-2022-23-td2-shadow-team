package commun;


import org.springframework.http.MediaType;

import java.util.Map;
import java.util.Random;

public class IJoueur {
    private String name;
    private Random rand;



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public Coup jouer(EtatDuJeu etat) {
        int val = rand.nextInt(100);
        System.out.println(getName()+"> je joue "+val);
        return new Coup("valeur aléatoire "+val, this);
    }
    public ScoreJoueur getvariableScore() {
        return null;
    }





}
