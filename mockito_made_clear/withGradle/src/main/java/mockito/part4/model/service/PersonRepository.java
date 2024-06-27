package mockito.part4.model.service;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findById(int id);
    Person save(Person person);

    List<Person> findAll();

    long count();

    void delete(Person person);
}