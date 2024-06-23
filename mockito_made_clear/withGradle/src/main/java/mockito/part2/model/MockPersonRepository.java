package mockito.part2.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MockPersonRepository implements PersonRepository {

    @Override
    public Optional<Person> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return Arrays.asList(
                new Person(1, "Grace", "Hopper", LocalDate.of(1906, Month.DECEMBER, 9)),
                new Person(2, "Ada", "Lovelace", LocalDate.of(1815, Month.DECEMBER, 10)),
                new Person(3, "Adele", "Goldberg", LocalDate.of(1945, Month.JULY, 7)),
                new Person(14, "Anita", "Borg", LocalDate.of(1949, Month.JANUARY, 17)),
                new Person(5, "Barbara", "Liskov", LocalDate.of(1939, Month.NOVEMBER, 7)));
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Person person) {
    }
}
