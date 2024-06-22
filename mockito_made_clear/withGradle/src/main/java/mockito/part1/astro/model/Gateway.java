package mockito.part1.astro.model;

// access the RESTful web service
@FunctionalInterface
public interface Gateway<T> {
    T getResponse();
}
