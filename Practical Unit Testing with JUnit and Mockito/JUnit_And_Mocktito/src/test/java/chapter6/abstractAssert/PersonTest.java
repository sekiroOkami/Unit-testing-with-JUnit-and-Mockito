package chapter6.abstractAssert;

import chapter6.abstractAssert.condition.AdultCondition;
import chapter6.abstractAssert.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {
    @ParameterizedTest
    @CsvSource({
            "Frodo, 38",
            "Sam, 37"
    })
    void personShouldHaveCorrectNameAndAge(String name, int age) {
        Person person = new Person(name, age);

        PersonAssert.assertThat(person)
                .hasName(name)
                .hasAge(age);
    }

    @Test
    void personShouldBeAndAdult() {
        var person = new Person("Frodo", 20);
        AdultCondition isAdult = new AdultCondition();

        assertThat(person).is(isAdult);
    }
}
