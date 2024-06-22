package mockito.part1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloMockitoTest {
    private HelloMockito helloMockito = new HelloMockito();
    @Test
    void greetPerson() {
        String greeting = helloMockito.greet("World");
        assertEquals("Hello, World, from Mockito!", greeting);
    }
}