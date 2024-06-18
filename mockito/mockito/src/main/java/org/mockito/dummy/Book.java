package org.mockito.dummy;

import java.time.LocalDate;

public record Book(String bookID, String title, int price, LocalDate publishedDate) {
}
