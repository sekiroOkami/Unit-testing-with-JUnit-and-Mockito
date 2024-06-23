package org.mockito.dummy;

import org.mockito.dummy.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DummyBookRepository implements BookRepository {

    // in memory db, HashMap or List
    Map<String, Book> bookStore = new HashMap<>();

    @Override
    public void save(Book book) {
        bookStore.put(book.bookID(), book);
    }

    @Override
    public Collection<Book> findAll() {
        return bookStore.values();
    }
}
