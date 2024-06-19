package chapter6.abstractAssert.condition;

import chapter6.abstractAssert.model.Person;
import org.assertj.core.api.Condition;

public class AdultCondition extends Condition<Person> {
    @Override
    public boolean matches(Person person) {
        return person.age() >= 18;
    }
}
