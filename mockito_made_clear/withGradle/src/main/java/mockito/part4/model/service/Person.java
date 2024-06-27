package mockito.part4.model.service;

import java.time.LocalDate;

public record Person(Integer id, String firstName, String lastName, LocalDate dateOfBirth) {

}
