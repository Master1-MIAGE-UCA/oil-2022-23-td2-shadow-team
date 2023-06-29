import commun.YamsPlayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YamsPlayerTest {

    @Test
    public void testSetAndGetName() {
        YamsPlayer yamsPlayer = new YamsPlayer();
        String name = "Player1";
        yamsPlayer.setName(name);
        assertEquals(name, yamsPlayer.getName());
    }
}
