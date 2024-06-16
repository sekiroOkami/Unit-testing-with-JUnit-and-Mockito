package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MoneyTest {


    @Test
    void moneyShouldHaveAmount() {
        Money money = new Money(10, "USD");
        assertThat(money.getAmount()).isEqualTo(10);
    }


    @Test
    void amountLessThanZero() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        ()-> new Money(-1, "USD")
                );
    }


}
