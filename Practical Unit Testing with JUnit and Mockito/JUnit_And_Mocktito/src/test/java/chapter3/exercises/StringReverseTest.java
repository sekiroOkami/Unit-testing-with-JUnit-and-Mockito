package chapter3.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.*;

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

    @ParameterizedTest
    @NullSource
    void shouldThrowNullPointer(String nullString) {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(
                        () -> StringReverse.reverse(nullString)
                );
    }
}