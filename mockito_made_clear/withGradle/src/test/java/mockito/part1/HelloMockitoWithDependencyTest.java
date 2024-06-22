package mockito.part1;

import mockito.part1.model.Person;
import mockito.part1.model.PersonRepository;
import mockito.part1.model.TranslationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HelloMockitoWithDependencyTest {

    @Mock
    private PersonRepository repository;

    @Mock
    private TranslationService translationService;

    @InjectMocks
    private HelloMockitoWithDependency helloMockitoWithDependency;

    @Test
    @DisplayName("Greet Admiral Hopper")
    void greetAPersonThatExists() {

        // config the mocks
        when(repository.findById(anyInt()))
                .thenReturn(Optional.of(new Person(1, "Grace", "Hopper", LocalDate.now())));
        when(translationService.translate(
                "Hello, Grace, from Mockito!", "en", "en"))
                .thenReturn("Hello, Grace, from Mockito!");

        // assert
        String greeting = helloMockitoWithDependency.greet(1, "en", "en");
        assertThat("Hello, Grace, from Mockito!").isEqualTo(greeting);

        // verify the methods are called once, in the right order
        InOrder inOrder = Mockito.inOrder(repository, translationService);
        inOrder.verify(repository).findById(anyInt());
        inOrder.verify(translationService).translate(anyString(), eq("en"), eq("en"));
    }

    @Test
    @DisplayName("Greet a person not in the database")
    void greetAPersonThatDoesNetExits() {
        // Set the expectations on the dependencies
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        when(translationService.translate("Hello, World, from Mockito!", "en", "en"))
                .thenReturn("Hello, World, from Mockito!");

        String greeting = helloMockitoWithDependency.greet(100, "en", "en");

        // verify the methods
        InOrder inOrder = inOrder(repository, translationService);
        inOrder.verify(repository).findById(anyInt());
        inOrder.verify(translationService).translate(anyString(), eq("en"), eq("en"));
    }
}