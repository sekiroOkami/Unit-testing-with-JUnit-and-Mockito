package mockito.part2.model.astro.model;

// access the RESTful web service
@FunctionalInterface
public interface Gateway<T> {
    T getResponse();
}
