package mockito.part1;

import mockito.part1.model.Person;
import mockito.part1.model.PersonRepository;
import mockito.part1.model.TranslationService;

import java.util.Optional;

public class HelloMockitoWithDependency {
    private String greeting = "Hello, %s, from Mockito!";
    private final PersonRepository personRepository;
    private final TranslationService translationService;

    public HelloMockitoWithDependency(PersonRepository personRepository, TranslationService translationService) {
        this.personRepository = personRepository;
        this.translationService = translationService;
    }

    public String greet(int id, String sourceLanguage, String targetLanguage) {
        Optional<Person> person = personRepository.findById(id);
        String name = person.map(Person::firstName).orElse("World");
        return translationService.translate(
                String.format(greeting, name), sourceLanguage, targetLanguage);
    }
}
