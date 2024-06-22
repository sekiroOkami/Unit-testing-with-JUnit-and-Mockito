package mockito.part1.astro.model;

import java.util.Map;
import java.util.stream.Collectors;

// convert the records returned by the gateway into A Java Map
public class AstroService {
    private final Gateway<AstroResponse> gateway;

    public AstroService(Gateway<AstroResponse> gateway) {
        this.gateway = gateway;
    }

    public Map<String, Long> getAstroData() {
        AstroResponse response = gateway.getResponse();
        return groupByCraft(response);
    }

    private Map<String, Long> groupByCraft(AstroResponse data) {
        return data.people().stream()
                .collect(Collectors.groupingBy(
                        Assignment::craft, Collectors.counting()
                ));
    }
}
