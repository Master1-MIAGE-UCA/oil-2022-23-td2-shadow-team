package yams;


import commun.Coup;
import commun.EtatDuJeu;
import commun.EtatJeuService;
import commun.YamsPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoueurWebController {
//    @Autowired
//    JoueurConcret joueurConcret;
//
//    @PostMapping("/jouer")
//    public Coup jouer(@RequestBody EtatDuJeu etatDuJeu) {
//        System.out.println("Joueur ["+ joueurConcret.getName()+"] > on me demande de jouer sur "+ etatDuJeu);
//        return joueurConcret.jouer(etatDuJeu);
//    }
//
//    @GetMapping("/nom")
//    public String getNom() {
//        return joueurConcret.getName();
//    }

    @Autowired
    AIStrategeService player;

    @PostMapping("/jouer")
    public String play(@RequestBody EtatJeuService etatJeuService) {
        System.out.println("Joueur ["+ player.getName()+"] > on me demande de jouer sur "+ etatJeuService.getNombreDeTour());
        player.play(etatJeuService);
        return "Player "+player.getName()+" is playing";
    }

    @GetMapping("/nom")
    public String getName() {
        return player.getName();
    }
}
