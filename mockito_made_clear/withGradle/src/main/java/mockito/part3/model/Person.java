package mockito.part3.model;

import java.time.LocalDate;

public record Person(Integer id, String firstName, String lastName, LocalDate dateOfBirth) {

}
