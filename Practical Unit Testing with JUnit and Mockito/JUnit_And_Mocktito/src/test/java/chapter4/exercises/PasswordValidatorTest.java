package chapter4.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorTest {

    PasswordValidator passwordValidator = null;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"123"})
    void shouldReturnNOTPASSLevel(String password) {
        var level = passwordValidator.validatePassword(password);
        assertThat(level.toString())
                .as("Password level : NOT_PASS")
                .isNotNull()
                .isEqualTo("NOT_PASS");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345"})
    void shouldReturnBADLevel(String password) {
        var level = passwordValidator.validatePassword(password);
        assertThat(level.toString())
                .as("Password level : BAD")
                .isNotNull()
                .isEqualTo("BAD");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345A"})
    void shouldReturnGOODLevel(String password) {
        var level = passwordValidator.validatePassword(password);
        assertThat(level.toString())
                .as("Password level : " + level)
                .isNotNull()
                .isEqualTo("GOOD");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345_A"})
    void shouldReturnSTRONGLevel(String password) {
        var level = passwordValidator.validatePassword(password);
        assertThat(level.toString())
                .as("Password level : " + level)
                .isNotNull()
                .isEqualTo("STRONG");
    }




}
