package chapter3;

public record Money(int amount, String currency) {
    public Money{
        if (amount < 0) throw new IllegalArgumentException();
    }
}
