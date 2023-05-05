package partie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import partie.AIJoueur;
import partie.Yams;

@RestController
public class PartieController {

    @GetMapping("/jouerPartie")
    public String jouerYams() {
        AIJoueur player1 = new AIJoueur("Player 1", 1);
        AIJoueur player2 = new AIJoueur("Player 2", 2);
        Yams yams = new Yams(player1, player2);
        yams.jouer();

        return "Fin de partie";
    }
}
