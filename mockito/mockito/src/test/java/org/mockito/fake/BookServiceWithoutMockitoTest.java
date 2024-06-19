package org.mockito.fake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class BookServiceWithoutMockitoTest {
    private BookService bookService;
    private FakeBookRepository fakeBookRepository;

    @Test
    void testFake() {
        fakeBookRepository = new FakeBookRepository();
        bookService = new BookService(fakeBookRepository);

        bookService.addBook(new Book("0", "LOTR", 256, LocalDate.now()));
        bookService.addBook(new Book("2", "DUNE", 256, LocalDate.now()));

        assertThat(bookService.findNumberOfBooks()).isEqualTo(2);
    }
}
