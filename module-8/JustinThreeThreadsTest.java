// Unit tests for JustinThreeThreads helper methods
// Ensure: length is correct & only allowed characters appear.

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JustinThreeThreadsTest {

    private static final char[] LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] DIGITS  = "0123456789".toCharArray();
    private static final char[] SYMBOLS = "!@#$%&*".toCharArray();

    @Test
    void lettersGenerationLengthAndCharset() {
        String s = JustinThreeThreads.generateRandomFromSet(LETTERS, 10_000, 42L);
        assertEquals(10_000, s.length());
        assertTrue(JustinThreeThreads.stringContainsOnly(s, LETTERS));
    }

    @Test
    void digitsGenerationLengthAndCharset() {
        String s = JustinThreeThreads.generateRandomFromSet(DIGITS, 10_000, 42L);
        assertEquals(10_000, s.length());
        assertTrue(JustinThreeThreads.stringContainsOnly(s, DIGITS));
    }

    @Test
    void symbolsGenerationLengthAndCharset() {
        String s = JustinThreeThreads.generateRandomFromSet(SYMBOLS, 10_000, 42L);
        assertEquals(10_000, s.length());
        assertTrue(JustinThreeThreads.stringContainsOnly(s, SYMBOLS));
    }

    @Test
    void zeroCountIsEmpty() {
        String s = JustinThreeThreads.generateRandomFromSet(LETTERS, 0, 7L);
        assertEquals(0, s.length());
    }

    @Test
    void negativeCountThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                JustinThreeThreads.generateRandomFromSet(LETTERS, -1, 1L));
    }
}
