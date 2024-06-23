package mockito.part2.model.service;

import mockito.part2.model.MockPersonRepository;
import mockito.part2.model.Person;
import mockito.part2.model.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private PersonService personService;
    private PersonRepository mockRepo;

    private final List<Person> people = Arrays.asList(
            new Person(1, "Grace", "Hopper", LocalDate.of(1906, Month.DECEMBER, 9)),
            new Person(2, "Ada", "Lovelace", LocalDate.of(1815, Month.DECEMBER, 10)),
            new Person(3, "Adele", "Goldberg", LocalDate.of(1945, Month.JULY, 7)),
            new Person(4, "Anita", "Borg", LocalDate.of(1949, Month.JANUARY, 17)),
            new Person(5, "Barbara", "Liskov", LocalDate.of(1939, Month.NOVEMBER, 7)));
    @BeforeEach
    void setUp() {
//        mockRepo = new MockPersonRepository();
        mockRepo = mock(PersonRepository.class);
        personService = new PersonService(mockRepo);
    }

    @Test
    void getLastNames_usingMockMethod() {

        // config mock
        when(mockRepo.findAll()).thenReturn(people);

        List<String> lastName = personService.getLastNames();

        assertThat(lastName).contains("Hopper","Lovelace","Goldberg","Borg","Liskov");
        verify(mockRepo).findAll();
    }

    @Test
    void defaultImplementations() {
        assertAll(
                ()-> assertNull(mockRepo.save(any(Person.class))),
                ()-> assertTrue(mockRepo.findById(anyInt()).isEmpty()),
                ()-> assertTrue(mockRepo.findAll().isEmpty()),
                ()-> assertEquals(0, mockRepo.count())
        );
    }

    @Test
    void findByIds_thenReturnWithMultipleArgs() {
        when(mockRepo.findById(anyInt()))
                .thenReturn(
                Optional.of(people.get(0)),
                Optional.of(people.get(1)),
                Optional.of(people.get(2)),
                Optional.of(people.get(3)),
                Optional.of(people.get(4)),
                Optional.empty()
        );
        var personList = personService.findByIds(0, 1, 2, 3, 4, 5);
        assertThat(personList).containsExactlyElementsOf(people);
    }


    @Disabled
    @Test
    void testMultipleCalls() {
        when(mockRepo.findById(anyInt()))
                .thenReturn(Optional.of(people.get(0)))
                .thenThrow(new IllegalArgumentException("Person with id not found"))
                .thenReturn(Optional.of(people.get(1)))
                .thenReturn(Optional.empty());
    }

    @Test
    void deleteAllWithNulls() {
        // config findAll to return a list containing nulls of type Person
        when(mockRepo.findAll()).thenReturn(Arrays.asList((Person) null));

        // This won't compile:
//        when(mockRepo.delete(null)).thenThrow(RuntimeException.class);

        // But this will
        doThrow(RuntimeException.class).when(mockRepo).delete(null);

//        assertThrows(RuntimeException.class,
//                ()-> personService.deleteAll()
//        );

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(
                ()->personService.deleteAll()
        );

        verify(mockRepo).delete(null);
    }
}