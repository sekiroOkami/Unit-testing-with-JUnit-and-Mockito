package org.mockito.dummy;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DummyTest {
    private BookService bookService;
    private DummyBookRepository dummyBookRepository;

    private EmailService emailService;

    @Test
    public void demoDummy() {
        emailService = new EmailService() {
            @Override
            public void send(String message) {
                throw new AssertionError("Message from EmailService");
                // this is dummy ! it does nothing.
            }
        };

        dummyBookRepository = new DummyBookRepository();
        bookService = new BookService(dummyBookRepository, emailService);

        bookService.addBook(new Book("0", "LOTR", 256, LocalDate.now()));
        bookService.addBook(new Book("2", "DUNE", 256, LocalDate.now()));

        assertAll(
                ()-> assertThat(bookService.findNumberOfBooks()).isEqualTo(2),
                ()-> assertThatExceptionOfType(AssertionError.class).isThrownBy(()-> emailService.send("hi"))
        );
    }
}
