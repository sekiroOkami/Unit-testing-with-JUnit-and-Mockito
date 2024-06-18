package chapter8.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserToPersonConverterTest {

    @Test
    void shouldConvertUserNamesIntoPersonNick() {
        String name = RandomStringUtils.randomAlphabetic(8);
        String surname = RandomStringUtils.randomAlphabetic(12);
        User user = new User(name, surname);

        Person person = UserToPersonConverter.convert(user);
        assertThat(person.name())
                .isEqualTo(name+ " " + surname);

    }

}
