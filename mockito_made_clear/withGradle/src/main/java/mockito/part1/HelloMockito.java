package mockito.part1;

import mockito.part1.model.Person;
import mockito.part1.model.PersonRepository;
import mockito.part1.model.TranslationService;

import java.util.Optional;

public class HelloMockito {
    private String greeting = "Hello, %s, from Mockito!";
    public String greet(String name) {
        return String.format(greeting, name);
    }
}
