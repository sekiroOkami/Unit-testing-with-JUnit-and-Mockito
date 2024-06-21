package org.mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class BookServiceTest {

    private BookService bookService;
    private SpyBookRepository spyBookRepository;

    @BeforeEach
    void setUp() {
//        spyBookRepository = mock(BookRepository.class);
        spyBookRepository = new SpyBookRepository();
        bookService = new BookService(spyBookRepository);
    }

    private static Stream<Book> providedBooks() {
        return Stream.of(
                new Book("0", "LOTR", 500, LocalDate.now()),
                new Book("1", "DUNE", 400, LocalDate.now())
        );
    }
    @ParameterizedTest
    @MethodSource("providedBooks")
    void shouldVerifyBehaviorOfSpy(Book book) {
        bookService.addBook(book);

        assertThat(spyBookRepository.getSaveCalled()).isEqualTo(1); // Expecting 1 call per test
        assertThat(spyBookRepository.calledWith(book)).isTrue();
    }
}