package yams;


import commun.Coup;
import commun.EtatDuJeu;
import commun.ScoreJoueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoueurWebController {
    @Autowired
    JoueurConcret joueurConcret;

    @PostMapping("/jouer")
    public Coup jouer(@RequestBody EtatDuJeu etatDuJeu) {
        System.out.println("Joueur ["+ joueurConcret.getName()+"] > on me demande de jouer sur "+ etatDuJeu);
        return joueurConcret.jouer(etatDuJeu);
    }

    @GetMapping("/nom")
    public String getNom() {
        return joueurConcret.getName();
    }

    @GetMapping("/score")
    public ScoreJoueur getvariableScore() {
        return joueurConcret.getvariableScore();
    }

}
