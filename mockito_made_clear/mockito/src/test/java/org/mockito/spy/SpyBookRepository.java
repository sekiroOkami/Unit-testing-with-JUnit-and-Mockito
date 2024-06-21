package org.mockito.spy;

public class SpyBookRepository implements BookRepository {

    int saveCalled = 0;
    Book lastAddedBook = null;
    @Override
    public synchronized void save(Book book) {
        saveCalled++;
        lastAddedBook = book;
    }

    public int getSaveCalled() {
        return saveCalled;
    }

    public boolean calledWith(Book book) {
        return lastAddedBook.equals(book);
    }
}
