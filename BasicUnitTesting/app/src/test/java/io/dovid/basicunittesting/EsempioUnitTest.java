package io.dovid.basicunittesting;

import org.junit.Test;

import static io.dovid.basicunittesting.Arithmetic.factorial;
import static org.junit.Assert.*;


public class EsempioUnitTest {

    @Test
    public void factorialLanciaEccezionePerNumeriNegativi() throws Exception {
        Exception exception = null;
        try {
            factorial(-1);
        } catch (ArithmeticException e) {
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void factorialLanciaEccezionePerNumeriMaggioriDi20() throws Exception {
        Exception exception = null;
        try {
            factorial(21);
        } catch (ArithmeticException e) {
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void factorial0() throws Exception {
        assertEquals("factorial funziona per 0", factorial(0), 1);
    }

    @Test
    public void factorial1() throws Exception {
        assertEquals("factorial funziona per 1", factorial(1), 1);
    }

    @Test
    public void factorial5() throws Exception {
        assertEquals("factorial funziona per 5", factorial(5), 120);
    }

    @Test
    public void factorial20() throws Exception {

        assertEquals("factorial funziona per 20", factorial(20), 2432902008176640000L);
    }
}