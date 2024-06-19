package chapter6.abstractAssert;

import chapter6.abstractAssert.model.Book;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class BookAssert extends AbstractAssert<BookAssert, Book> {

    public BookAssert(Book actual) {
        super(actual, BookAssert.class);
    }

    public static BookAssert assertThat(Book actual) {
        return new BookAssert(actual);
    }

    public BookAssert hasTitle(String title) {
        isNotNull();
        Assertions.assertThat(actual.title())
                .as("title")
                .isEqualTo(title);
        return this;
    }

    public BookAssert isWrittenIn(String language) {
        isNotNull();
        Assertions.assertThat(actual.language()).isEqualTo(language);
        return this;
    }
}
