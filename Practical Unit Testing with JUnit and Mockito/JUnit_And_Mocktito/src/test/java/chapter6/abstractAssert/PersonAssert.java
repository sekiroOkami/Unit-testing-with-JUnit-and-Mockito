package chapter6.abstractAssert;

import chapter6.abstractAssert.model.Person;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class PersonAssert extends AbstractAssert<PersonAssert, Person> {

    // init with actual 'Person' object
    public PersonAssert(Person actual) {
        super(actual, PersonAssert.class);
    }

    // create an instance of 'PersonAssert'
    public static PersonAssert assertThat(Person actual) {
        return new PersonAssert(actual);
    }


    public PersonAssert hasName(String name) {
        isNotNull();
        Assertions.assertThat(actual.name())
                .as("Name")
                .isEqualTo(name);
        return this;
    }

    public PersonAssert hasAge(int age) {
        isNotNull();
        Assertions.assertThat(actual.age())
                .as("Name")
                .isEqualTo(age);
        return this;
    }


}
