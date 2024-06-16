package chapter4;

public record FootBallTeam(int gameWon) implements Comparable<FootBallTeam> {
    public FootBallTeam{
        if (gameWon < 0) throw new IllegalArgumentException("Number of game won cannot < 0");
    }

    @Override
    public int compareTo(FootBallTeam otherTeam) {
        return Integer.compare(gameWon, otherTeam.gameWon);
    }
}
