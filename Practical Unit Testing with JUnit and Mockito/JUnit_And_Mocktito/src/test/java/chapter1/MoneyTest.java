package chapter1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MoneyTest {
    public static final String VALID_CURRENCY = "USD";
    @Test
    void constructorShouldSetAmountAndCurrency() {
        var money = new Money(10, "USD" );
        assertThat(money.amount()).isEqualTo(10);
        assertThat(money.currency()).isEqualTo("USD");
    }

    @ParameterizedTest
    @CsvSource({
            "10, USD",
            "15, EUR",
            "50, CHF"
    })
    void constructorShouldSetAmountAndCurrencyWithParameter(int amount, String currency) {
        var money = new Money(amount, currency);
        assertThat(money.currency()).isEqualTo(currency);
        assertThat(money.amount()).isEqualTo(amount);
    }

//    @Test
//    void testException() {
//        assertThatExceptionOfType(MyException.class)
//                .isThrownBy(
//                        ()-> {
//                            SUT.someMethod();
//                        }
//                )
//    }
    @ParameterizedTest
    @ValueSource(ints = {-5454545, -12, -3})
    void shouldThrowIllegalArgument(int invalidAmount) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        ()-> new Money(invalidAmount, VALID_CURRENCY)
                );
    }

}