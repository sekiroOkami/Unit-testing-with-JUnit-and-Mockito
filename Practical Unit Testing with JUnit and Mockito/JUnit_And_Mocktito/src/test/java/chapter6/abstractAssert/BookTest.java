package chapter6.abstractAssert;

import chapter6.abstractAssert.model.Book;
import org.assertj.core.api.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BookTest {

    private static final String ENGLISH = "English";
    private Book demoBook;

//    Condition<Book> writtenInEnglish = new Condition<>(
//            book -> ENGLISH.equals(book.language())
//    );

    @ParameterizedTest
    @CsvSource({
            "LOTR, ENGLISH",
            "DUNE2, ENGLISH"
    })
    void shoutReturnATitle(String title, String language) {
        Book book = new Book(title, language);
        BookAssert.assertThat(book)
                .hasTitle(title);

    }
}
