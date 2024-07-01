package mockito.part5.model.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InMemoryPersonRepositoryTest {

    private final List<Person> people = List.of(
            new Person(1, "Grace", "Hopper", LocalDate.of(1906, Month.DECEMBER, 9)),
            new Person(2, "Ada", "Lovelace", LocalDate.of(1815, Month.DECEMBER, 10)),
            new Person(3, "Adele", "Goldberg", LocalDate.of(1945, Month.JULY, 7)),
            new Person(14, "Anita", "Borg", LocalDate.of(1949, Month.JANUARY, 17)),
            new Person(5, "Barbara", "Liskov", LocalDate.of(1939, Month.NOVEMBER, 7)));

    @Test
    void testMockOfFinalMethod() {
        // can mock a class containing final methods
        var personRepo = mock(InMemoryPersonRepository.class);

        // set the expectations on (final) save method in the mock
        when(personRepo.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // inject the mock
        var personService = new PersonService(personRepo);

        List<Integer> ids = personService.savePeople(people.toArray(Person [] ::new));
        assertThat(ids).containsExactly(1, 2, 3, 14, 5);

        // verify the save method in the mock was invoked five times
        verify(personRepo, times(5)).save(any(Person.class));
    }

}