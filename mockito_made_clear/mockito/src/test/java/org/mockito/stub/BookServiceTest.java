package org.mockito.stub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest {
    BookService bookService;

    BookRepository stubBookRepository;

    @BeforeEach
    void setUp() {
        stubBookRepository = new BookRepository() {
            @Override
            public List<Book> findNewBooks(int lookBackDays) {
                return List.of(
                        new Book("0", "LOTR", 500, LocalDate.now()),
                        new Book("0", "LOTR", 400, LocalDate.now())
                );
            }
        };
        bookService = new BookService(stubBookRepository);
    }

    @Test
    void shouldReturnNewCollectionOfBooksWithDiscountPrice() {
        List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 20);

        assertThat(newBooksWithAppliedDiscount)
                .isNotNull()
                .hasSize(2)
                .extracting(Book::price)
                .containsExactlyInAnyOrder(450, 360);
    }


}