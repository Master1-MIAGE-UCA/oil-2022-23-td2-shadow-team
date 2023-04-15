package com.mycompany.app.oil202223td2shadowteam;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import temp.Calculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        Calculator calculator = mock(Calculator.class);

        when(calculator.add(2, 3)).thenReturn(5);

        int result = calculator.add(2, 3);

        verify(calculator).add(2, 3);
        assertEquals(5, result);

        LOGGER.info("\u001B[32m" + "All tests passed successfully!" + "\u001B[0m");
    }
}
