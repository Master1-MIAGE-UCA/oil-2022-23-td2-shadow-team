package yams;
import commun.Decision;
import commun.EtatJeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoueurWebController {

    @Autowired
    AIStrategeService player;

    @PostMapping("/jouer")
    public Decision play(@RequestBody EtatJeuService etatJeuService) {
        System.out.println("Joueur [" + player.getName() + "] > on me demande de jouer au tour " + etatJeuService.getNombreDeTour());
        return player.play(etatJeuService);
    }

    @GetMapping("/nom")
    public String getName() {
        return player.getName();
    }
}
