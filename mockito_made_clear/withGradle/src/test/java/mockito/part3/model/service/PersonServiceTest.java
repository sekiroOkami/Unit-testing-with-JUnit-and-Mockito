package mockito.part3.model.service;

import mockito.part3.model.Person;
import mockito.part3.model.PersonRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService personService;


    @Test
    void findIdThatDoesNotExist() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        List<Person> byIds = personService.findByIds(999);
        assertTrue(byIds.isEmpty());
        verify(repository).findById(anyInt());
    }

    @Test
    @Disabled("do not use argThat with integers")
    void findByIdsThatDoNotExist_argThat() {
        when(repository.findById(argThat(id-> id > 14))).thenReturn(Optional.empty());
        List<Person> personsList = personService.findByIds(15, 42, 78, 999);
        assertTrue(personsList.isEmpty());
        verify(repository, times(4)).findById(anyInt());
    }

    @Test
    void findByIdsThatDoNotExist_intThat() {
        when(repository.findById(intThat(id->id > 14))).thenReturn(Optional.empty());
        List<Person> personsList = personService.findByIds(15, 42, 78, 999);
        assertTrue(personsList.isEmpty());
        verify(repository, times(4)).findById(anyInt());
    }

}