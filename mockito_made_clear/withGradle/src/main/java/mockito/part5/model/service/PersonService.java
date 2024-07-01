package mockito.part5.model.service;

import mockito.part5.model.service.Person;
import mockito.part5.model.service.PersonRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<String> getLastNames() {
        return
        personRepository.findAll().stream()
                .map(person -> person.lastName())
                .collect(Collectors.toList());
    }

    public List<Person> findByIds(int... ids) {
        return Arrays.stream(ids)
                .mapToObj(personRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        personRepository.findAll()
                .forEach(personRepository::delete);
    }

    public Integer findMaxId() {
        return personRepository.findAll().stream()
                .map(Person::id)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public Person createPerson(int id, String first, String last, LocalDate dob) {
        var person = new Person(id, first, last, dob);
        return personRepository.save(person);
    }

    public List<Integer> savePeople(Person... persons) {
        return Arrays.stream(persons)
                .map(personRepository::save)
                .map(Person::id)
                .collect(Collectors.toList());
    }
}
