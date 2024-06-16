package chapter4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FootBallTeamTest {
    private FootBallTeam footBallTeam;
    public static final int VALID_GAME_WON =3;
    public static final int ANY_VALUE = 10;


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

    @Test
    void teamWithLessMatchesWonShouldBeLesser() {
        FootBallTeam otherTeam = new FootBallTeam(4);
        assertThat(footBallTeam.compareTo(otherTeam))
                .as("Less than 0")
                .isLessThan(0);
    }

    @Test
    @DisplayName("Comparison test")
    void teamWithGreaterMatchesWonShouldBeGreater() {
        FootBallTeam otherTeam = new FootBallTeam(1);
        assertThat(footBallTeam.compareTo(otherTeam))
                .as("Greater than 0")
                .isGreaterThan(0);
    }

    @Test
    @DisplayName("Comparison test")
    void teamWithSameMatchesWonShouldBeZero() {
        FootBallTeam otherTeam = new FootBallTeam(3);
        assertThat(footBallTeam.compareTo(otherTeam))
                .as("Greater than 0")
                .isEqualTo(0);
    }








}
