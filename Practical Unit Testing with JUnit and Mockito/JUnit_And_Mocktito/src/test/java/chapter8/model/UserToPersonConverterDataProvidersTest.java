package chapter8.model;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class UserToPersonConverterDataProvidersTest {
    private static Stream<Arguments> getRandomNames() {
        return Stream.generate(() -> {
            return Arguments.of(
                    Faker.instance().name().firstName(),
                    Faker.instance().name().lastName()
            );
        }).limit(100);
    }
    @ParameterizedTest
    @MethodSource("getRandomNames")
    void shouldConvertUserNamesIntoPersonNick(
            String name,  String surname
    ) {
        User user = new User(name, surname);
        Person person = UserToPersonConverter.convert(user);

        assertThat(person.name())
                .isEqualTo(name + " " + surname);
    }
}
