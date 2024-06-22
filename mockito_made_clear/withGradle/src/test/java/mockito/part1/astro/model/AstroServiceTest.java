package mockito.part1.astro.model;

import mockito.part1.astro.model.fake.FakeGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AstroServiceTest {
    private AstroService astroService;
    private Gateway astroGateway;

    @Test
    void testAstroData_usingRealGateway_withHttpClient() {
        // Create an SUT of AstroService using the real Gateway
        astroGateway = new AstroGateway();
        astroService = new AstroService(astroGateway);

        // Call method
        Map<String, Long> astroData = astroService.getAstroData();

        astroData.forEach((craft, number) -> {
            System.out.println(number + " astronauts aboard " + craft);
            assertAll(
                    ()-> assertThat(number).isPositive(),
                    ()-> assertThat(craft).isNotBlank()
            );
        });
    }


    @Test
    void testAstroData_usingOwnFakeGateWay() {
        // Create SUT
        FakeGateway fakeGateway  = new FakeGateway();
        astroService = new AstroService(fakeGateway);

        // Act
        Map<String, Long> astroData = astroService.getAstroData();

        // Assert
        astroData.forEach((craft, number) -> {
            System.out.println(number + " astronauts aboard " + craft);
            assertAll(
                    ()-> assertThat(number).isPositive(),
                    ()-> assertThat(craft).isNotBlank()
            );
        });
    }

    @ParameterizedTest
    @MethodSource("mockito.part1.astro.model.AstroDataProvider#astroData")
    @DisplayName("Parameterized test for getAstroData method")
    void testAstroData_usingParameterize(List<Assignment> assignments, Map<String, Long> expectedResult) {

        // Arrange SUT
        Gateway<AstroResponse> fakeGateway = () -> new AstroResponse(7, "Success", assignments);
        astroService = new AstroService(fakeGateway);

        // Act
        Map<String, Long> astroData = astroService.getAstroData();

        // Assert
        expectedResult.forEach((craft, number) -> {
            System.out.println(number + " astronauts aboard " + craft);
            assertAll(
                    () -> assertThat(astroData.get(craft)).isEqualTo(number),
                    () -> assertThat(craft).isNotBlank()
            );
        });
    }


}