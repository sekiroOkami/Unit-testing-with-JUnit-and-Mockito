package org.mockito.dummy;

public class BookService {

    private BookRepository bookRepository;
    private EmailService emailService;

    public BookService(BookRepository bookRepository, EmailService emailService) {
        this.bookRepository = bookRepository;
        this.emailService = emailService;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public int findNumberOfBooks() {
        return bookRepository.findAll().size();
    }

    // other method which use EmailService

}
