package partie.controller;

import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DesController {

    @GetMapping("/lancemenDes")
    public int rollDice() {
        // Créer une instance de la classe Random
        Random random = new Random();

        // Générer un nombre aléatoire entre 1 et 6 (inclus)
        int resultatLancerDe = random.nextInt(6) + 1;

        return resultatLancerDe;
    }
}
