package appariement;


import org.springframework.stereotype.Component;
import partie.Partie;
import yams.Joueur;

@Component
public class Lancement {

    public void demarrer() {
        Partie p = new Partie();
        Joueur j1 = new Joueur("Mic");
        Joueur j2 = new Joueur("Hel");
        p.addIJoueur(j1);
        p.addIJoueur(j2);
        p.demarrer();

    }
}