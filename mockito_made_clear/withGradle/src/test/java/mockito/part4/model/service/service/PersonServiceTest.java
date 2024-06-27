package mockito.part4.model.service.service;


import mockito.part4.model.service.InMemoryPersonRepository;
import mockito.part4.model.service.Person;
import mockito.part4.model.service.PersonRepository;
import mockito.part4.model.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private final List<Person> people = List.of(
            new Person(1, "Grace", "Hopper", LocalDate.of(1906, Month.DECEMBER, 9)),
            new Person(2, "Ada", "Lovelace", LocalDate.of(1815, Month.DECEMBER, 10)),
            new Person(3, "Adele", "Goldberg", LocalDate.of(1945, Month.JULY, 7)),
            new Person(14, "Anita", "Borg", LocalDate.of(1949, Month.JANUARY, 17)),
            new Person(5, "Barbara", "Liskov", LocalDate.of(1939, Month.NOVEMBER, 7)));

    private final Map<Integer, Person> peopleMap = people.stream()
            .collect(Collectors.toMap(Person::id, p -> p));

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService personService;

    @Captor
    private ArgumentCaptor<Person> personArg;


    @Test
    void findMaxId() {
        given(repository.findAll()).willReturn(people);
        assertThat(personService.findMaxId()).isEqualTo(14);
        then(repository).should().findAll();
    }

    @Test
    void createPersonUsingDateString() {
        Person hopper = people.get(0);
        when(repository.save(hopper)).thenReturn(hopper);
        Person actual = personService.createPerson(1, "Grace", "Hopper", LocalDate.of(1906, 12, 9));
        verify(repository).save(personArg.capture());
        assertThat(personArg.getValue()).isEqualTo(hopper);
        assertThat(actual).isEqualTo(hopper);
    }

    @Test
    @DisplayName("Test savePeople by using Answer")
    void userAnswer() {
        // lambda expression implementation of Answer<Person>
        when(repository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));

        List<Integer> ids = personService.savePeople(people.toArray(Person [] :: new));

        List<Integer> actuals = people.stream().map(Person::id).toList();
        assertThat(ids).isEqualTo(actuals);
    }

    @Test
    void testInMemoryPersonRepository() {
        var personRepo = new InMemoryPersonRepository();
        var personService = new PersonService(personRepo);

        personService.savePeople(people.toArray(Person[] :: new));
        assertThat(personRepo.findAll()).isEqualTo(people);
    }

    @Test
    void spyOnRepository() {
        // Spy on the SUT
        PersonRepository personRepo = spy(InMemoryPersonRepository.class);
        PersonService personService = new PersonService(personRepo);

        personService.savePeople(people.toArray(Person[]::new));
        assertThat(personRepo.findAll()).isEqualTo(people);

        // verify
        verify(personRepo, times(people.size())).save(any(Person.class));
    }



}