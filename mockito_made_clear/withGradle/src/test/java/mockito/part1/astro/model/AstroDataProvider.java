package mockito.part1.astro.model;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class AstroDataProvider {
    public static Stream<Arguments> astroData() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Assignment("Kathryn Janeway", "USS Voyager"),
                                new Assignment("Seven of Nine", "USS Voyager"),
                                new Assignment("Will Robinson", "Jupiter 2"),
                                new Assignment("Lennier", "Babylon 5"),
                                new Assignment("James Holden", "Rocinante"),
                                new Assignment("Naomi Negata", "Rocinante"),
                                new Assignment("Ellen Ripley", "Nostromo")
                        ),
                        Map.of(
                                "USS Voyager", 2L,
                                "Jupiter 2", 1L,
                                "Babylon 5", 1L,
                                "Rocinante", 2L,
                                "Nostromo", 1L
                        )
                )
        );
    }
}
