import commun.ProbaParam;
import commun.constants.TypeCombinaison;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbaParamTest {

    @Test
    public void testSetAndGetCombinaison() {
        ProbaParam probaParam = new ProbaParam();
        probaParam.setTypeCombinaison(TypeCombinaison.YAMS);
        assertEquals(TypeCombinaison.YAMS, probaParam.getTypeCombinaison());
    }

    @Test
    public void testSetAndGetDes() {
        ProbaParam probaParam = new ProbaParam();
        List<Integer> des = Arrays.asList(1, 2, 3, 4, 5);
        probaParam.setDes(des);
        assertEquals(des, probaParam.getDes());
    }

    @Test
    public void testSetAndGetPlayerName() {
        ProbaParam probaParam = new ProbaParam();
        String playerName = "Player1";
        probaParam.setPlauerName(playerName);
        assertEquals(playerName, probaParam.getPlauerName());
    }
}
