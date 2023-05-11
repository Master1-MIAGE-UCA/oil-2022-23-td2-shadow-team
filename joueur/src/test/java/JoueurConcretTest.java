

import commun.Coup;
import commun.EtatDuJeu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yams.JoueurConcret;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JoueurConcretTest {

    private JoueurConcret joueurConcret;

    @BeforeEach
    void setUp() {
        joueurConcret = new JoueurConcret();
        joueurConcret.setName("John Doe");
    }

    @Test
    void testJouer() {
        EtatDuJeu etatDuJeu = new EtatDuJeu();
        Coup coup = joueurConcret.jouer(etatDuJeu);

        assertNotNull(coup);
        assertNotNull(coup.toString());
        assertEquals("John Doe", coup.getIJoueur().getName());
    }
}
