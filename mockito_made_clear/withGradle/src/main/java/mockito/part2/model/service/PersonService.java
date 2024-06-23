package mockito.part2.model.service;

import mockito.part2.model.PersonRepository;
import mockito.part2.model.Person;
import java.util.*;
import java.time.*;
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
}
