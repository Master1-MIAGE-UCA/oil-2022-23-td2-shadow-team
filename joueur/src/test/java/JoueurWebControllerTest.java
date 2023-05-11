

import commun.Coup;
import commun.EtatDuJeu;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import yams.JoueurApplication;
import yams.JoueurConcret;
import yams.JoueurWebController;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(controllers = JoueurWebController.class)
@Import(JoueurApplication.class)

public class JoueurWebControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private JoueurConcret joueurConcret;

    @Test
    public void testGetNom() throws Exception {
        String expectedName = "Player 1";
        when(joueurConcret.getName()).thenReturn(expectedName);

        webTestClient.get().uri("/nom")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo(expectedName);
    }

    @Test
    public void testJouer() throws Exception {
        EtatDuJeu etatDuJeu = new EtatDuJeu(); // Initialize with appropriate values
        Coup expectedCoup = new Coup("valeur aléatoire 42", joueurConcret);
        when(joueurConcret.jouer(etatDuJeu)).thenReturn(expectedCoup);

        ObjectMapper objectMapper = new ObjectMapper();
        String etatDuJeuJson = objectMapper.writeValueAsString(etatDuJeu);

        webTestClient.post().uri("/jouer")
                .contentType(APPLICATION_JSON)
                .bodyValue(etatDuJeuJson)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.description").isEqualTo("valeur aléatoire 42")
                .jsonPath("$.joueur.name").isEqualTo(joueurConcret.getName());
    }
}
