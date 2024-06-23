package mockito.part2.model.astro.model.fake;

import mockito.part2.model.astro.model.Assignment;
import mockito.part2.model.astro.model.AstroResponse;
import mockito.part2.model.astro.model.Gateway;

import java.util.List;

// A Stub for the Gateway
public class FakeGateway implements Gateway<AstroResponse> {
    @Override
    public AstroResponse getResponse() {
        return new AstroResponse(7, "Success",
                List.of(
                        new Assignment("Kathryn Janeway", "USS Voyager"),
                        new Assignment("Seven of Nine", "USS Voyager"),
                        new Assignment("Will Robinson", "Jupiter 2"),
                        new Assignment("Lennier", "Babylon 5"),
                        new Assignment("James Holden", "Rocinante"),
                        new Assignment("Naomi Negata", "Rocinante"),
                        new Assignment("Ellen Ripley", "Nostromo")
                ));
    }
}
