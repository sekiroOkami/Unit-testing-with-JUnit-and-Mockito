package chapter4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FootBallTeamTest {
    private FootBallTeam footBallTeam;
    public static final int VALID_GAME_WON =3;

    @BeforeEach
    void setUp() {
        footBallTeam = new FootBallTeam(VALID_GAME_WON);
    }


    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    void constructorShouldSetGamesWon(int numberOfGameWon) {
        footBallTeam = new FootBallTeam(numberOfGameWon);
        assertThat(footBallTeam.gameWon())
                .as("Number of Game Won")
                .isEqualTo(numberOfGameWon);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void constructorShouldThrowIllegalArgumentException(int illegalParam) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .as("IllegalArgument Exception must be thrown.")
                .isThrownBy(
                        ()-> footBallTeam = new FootBallTeam(illegalParam)
                );
    }

    @Test
    void footBallTeamMustBeComparable() {
        assertThat(footBallTeam)
                .as("Comparable class")
                .isInstanceOf(Comparable.class);
    }






}
