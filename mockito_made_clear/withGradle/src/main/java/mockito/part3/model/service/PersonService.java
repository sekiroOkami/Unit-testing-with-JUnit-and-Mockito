package mockito.part3.model.service;

import mockito.part3.model.Person;
import mockito.part3.model.PersonRepository;

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
}
