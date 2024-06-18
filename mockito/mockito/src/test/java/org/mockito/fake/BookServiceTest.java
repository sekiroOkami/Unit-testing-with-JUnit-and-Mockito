package org.mockito.fake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {
    private BookService bookService;
    private FakeBookRepository fakeBookRepository;

    @BeforeEach
    void setUp() {
        fakeBookRepository = mock(FakeBookRepository.class);
        bookService = new BookService(fakeBookRepository);
    }

    @Test
    void testFakeBookRepository() {

        when(fakeBookRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Book("0", "LOTR", 256, LocalDate.now()),
                        new Book("1", "JUnit 5 in Action", 200, LocalDate.now())
                ));
        assertThat(bookService.findNumberOfBooks())
                .as("# Books")
                .isEqualTo(2);
    }
}