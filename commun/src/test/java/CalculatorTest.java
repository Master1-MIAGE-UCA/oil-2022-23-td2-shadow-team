import commun.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private static final Logger LOGGER = LogManager.getLogger(CalculatorTest.class);

    @Test
    public void testAdditionner() {
        Calculator calculator = new Calculator();

        // Test avec des nombres positifs
        int resultat = calculator.add(2, 3);
        assertEquals(5, resultat);

        // Test avec des nombres négatifs
        resultat = calculator.add(-2, -3);
        assertEquals(-5, resultat);

        // Test avec un nombre nul
        resultat = calculator.add(0, 10);
        assertEquals(10, resultat);

        LOGGER.info("\u001B[32m" + "All tests passed successfully!" + "\u001B[0m");
    }


    @Test
    public void testAdd() {
        Calculator calculator = Mockito.mock(Calculator.class);

        Mockito.when(calculator.add(2, 3)).thenReturn(5);

        int result = calculator.add(2, 3);

        Mockito.verify(calculator).add(2, 3);
        assertEquals(5, result);

        LOGGER.info("\u001B[32m" + "All tests passed successfully!" + "\u001B[0m");
    }
}
