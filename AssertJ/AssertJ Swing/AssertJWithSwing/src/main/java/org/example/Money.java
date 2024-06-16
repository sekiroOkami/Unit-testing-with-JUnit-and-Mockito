package org.example;

public class Money {
    private int amount;
    public Money(int amount, String currency) {
        if (amount < 0) throw new IllegalArgumentException();
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
