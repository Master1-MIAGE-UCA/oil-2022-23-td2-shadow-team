import appariement.Appariement;
import appariement.AppariementWebControleur;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppariementWebControleurTest {

    @InjectMocks
    AppariementWebControleur appariementWebControleur;

    @Mock
    Appariement appariement;

    @Test
    public void testAddAPlayer() {
        MockitoAnnotations.openMocks(this);

        String urlPlayer = "http://playerurl.com";
        String urlPartie = "http://partieurl.com";

        Mockito.when(appariement.aadPlayer(urlPlayer)).thenReturn(urlPartie);

        String result = appariementWebControleur.addAPlayer(urlPlayer);
        assertEquals(urlPartie, result);
    }

    @Test
    public void testAjouterPartie() {
        MockitoAnnotations.openMocks(this);

        String urlPlayer1 = "http://player1url.com";
        String urlPlayer2 = "http://player2url.com";
        String urlPartie = "http://partieurl.com";

        Mockito.when(appariement.addPartie(urlPartie)).thenReturn(new String[]{urlPlayer1, urlPlayer2});

        String[] result = appariementWebControleur.ajouterPartie(urlPartie);
        assertEquals(urlPlayer1, result[0]);
        assertEquals(urlPlayer2, result[1]);
    }
}
