package org.mockito.stub;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getNewBooksWithAppliedDiscount(int percentDiscountRate, int lookBackDays) {
        List<Book> newBooks = bookRepository.findNewBooks(lookBackDays);
        return
        newBooks.stream()
                .map(book -> new Book(book.bookID(), book.title(), applyDiscount(book.price(), percentDiscountRate),
                        book.publishedDate()))
                .collect(Collectors.toList());
    }

    private int applyDiscount(int amount, int percentDiscountRate) {
        return amount - (amount * percentDiscountRate / 100);
    }
}
