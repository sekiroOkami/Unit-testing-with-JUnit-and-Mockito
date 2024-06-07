package chapter1;

public record Money(int amount, String currency) {
    public Money{
        if (amount < 0) throw new IllegalArgumentException();
    }
}
