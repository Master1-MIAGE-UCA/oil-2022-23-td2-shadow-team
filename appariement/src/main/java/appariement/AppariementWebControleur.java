package appariement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppariementWebControleur {
    @Autowired
    Appariement appariement;

    @PostMapping("/ajouterJoueur")
    public String addAPlayer(@RequestBody String urlPlayer){
        System.out.println("Appariement > alerte a new player arrived "+urlPlayer);
        return appariement.aadPlayer(urlPlayer);
    }


    @PostMapping("/ajouterPartie")
    public String[] ajouterPartie(@RequestBody String partieUrl){
        System.out.println("appariement > un nouvelle partie se signale "+partieUrl);
        return appariement.addPartie(partieUrl);
    }



}
