package mockito.part1.astro.model;

import java.util.List;

// show the total 'number' of people in space, a success 'message', and the people
// array of list of the assignments.
public record AstroResponse(int number, String message, List<Assignment> people) {
}
