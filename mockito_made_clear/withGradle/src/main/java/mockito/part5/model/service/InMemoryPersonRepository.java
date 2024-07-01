package mockito.part5.model.service;

import mockito.part5.model.service.Person;
import mockito.part5.model.service.PersonRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * The issues with this approach;
 *
 * 1. we have to implement all the method in the interface, even though out test
 * requires only one of them
 * 2. we have to maintain this class, so if our repository method change,
 * the stub will have to be updated as well.
 * 3. if we want to test failure modes, validation, or exceptional cases,
 * we'll need either additional classes oro some logic that lets us choose those alternatives.
 */
public class InMemoryPersonRepository implements PersonRepository {

    private final List<Person> people =
            Collections.synchronizedList(new ArrayList<>());

    @Override
    public Optional<Person> findById(int id) {
        return Optional.empty();
    }

    @Override
    public final Person save(Person person) {
        synchronized (people) {
            people.add(person);
        }
        return person;
    }

    @Override
    public List<Person> findAll() {
        return people;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public final void delete(Person person) {
        synchronized (people) {
            people.remove(person);
        }
    }
}
