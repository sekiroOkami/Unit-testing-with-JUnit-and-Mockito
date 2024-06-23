package org.mockito.spy;

import java.time.LocalDate;

public record Book(String bookID, String title, int price, LocalDate publishedDate) {
}
