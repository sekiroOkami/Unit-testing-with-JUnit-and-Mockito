package chapter1.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringReverseTest {
    private StringReverse stringReverse;

    @BeforeEach
    void setUp() {
        stringReverse = new StringReverse();
    }

    @ParameterizedTest
    @CsvSource({
            "tenet, tenet",
            "hannah, hannah",
            "hello, olleh",
            "JUnit, tinUJ"
    })
    void shouldReturnReverseString(String input, String expected) {
        assertThat(StringReverse.reverse(input)).isEqualTo(expected);
    }
}