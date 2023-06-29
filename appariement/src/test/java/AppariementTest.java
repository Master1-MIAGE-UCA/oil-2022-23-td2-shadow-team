import appariement.Appariement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppariementTest {

    @Test
    public void testAadPlayer() {
        Appariement appariement = new Appariement();
        String urlPlayer = "http://playerurl.com";
        String urlPartie = "http://partieurl.com";

        // Quand aucune partie n'est ajoutée
        String result = appariement.aadPlayer(urlPlayer);
        assertEquals("", result);

        // Ajouter une partie et tester à nouveau
        appariement.addPartie(urlPartie);
        result = appariement.aadPlayer(urlPlayer);
        assertEquals(urlPartie, result);
    }

    @Test
    public void testAddPartie() {
        Appariement appariement = new Appariement();
        String urlPlayer1 = "http://player1url.com";
        String urlPlayer2 = "http://player2url.com";
        String urlPartie = "http://partieurl.com";

        // Quand aucun joueur n'est ajouté
        String[] result = appariement.addPartie(urlPartie);
        assertNull(result);

        // Ajouter des joueurs et tester à nouveau
        appariement.aadPlayer(urlPlayer1);
        appariement.aadPlayer(urlPlayer2);
        result = appariement.addPartie(urlPartie);
        assertArrayEquals(new String[]{urlPlayer1, urlPlayer2}, result);
    }
}
