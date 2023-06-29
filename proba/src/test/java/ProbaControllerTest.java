package yams;

import commun.ProbaParam;
import commun.constants.TypeCombinaison;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProbaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProbaService probaService;

    @BeforeEach
    public void setUp() {
        /*
        when(probaService.calculeProbabilite(any(), any())).thenReturn(0.5);
 */
    }

    @Test
    public void testGetProba() throws Exception {
        /*
        ProbaParam probaParam = new ProbaParam("Test Player", Arrays.asList(1, 2, 3, 4, 5), TypeCombinaison.BRELAN);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/yams/proba/obtenirProba")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"playerName\":\"Test Player\",\"des\":[1,2,3,4,5],\"typeCombinaison\":\"BRELAN\"}");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(0.5));

         */
    }
}
