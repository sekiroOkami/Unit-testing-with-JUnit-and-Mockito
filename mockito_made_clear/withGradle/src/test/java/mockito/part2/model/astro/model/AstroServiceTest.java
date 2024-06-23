package mockito.part2.model.astro.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AstroServiceTest {

    private final AstroResponse mockAstroResponse =
            new AstroResponse(7, "Success", Arrays.asList(
                    new Assignment("John Sheridan", "Babylon 5"),
                    new Assignment("Susan Ivanova", "Babylon 5"),
                    new Assignment("Beckett Mariner", "USS Cerritos"),
                    new Assignment("Brad Boimler", "USS Cerritos"),
                    new Assignment("Sam Rutherford", "USS Cerritos"),
                    new Assignment("D'Vana Tendi", "USS Cerritos"),
                    new Assignment("Ellen Ripley", "Nostromo")
            ));
    @Mock
    private Gateway<AstroResponse> gateway;

    @InjectMocks
    private AstroService astroService;


    @Test
    void testAstroData_usingInjectedMockGateway() {

        // Mock Gateway created and injected into AstroService using
        // @Mock and @InjectMock annotations
        // create SUT mock

        // Config mock
        when(gateway.getResponse()).thenReturn(mockAstroResponse);

        // Act the method under test
        Map<String, Long> astroData = astroService.getAstroData();

        // Assert
        assertThat(astroData)
                .containsEntry("Babylon 5", 2L)
                .containsEntry("Nostromo", 1L)
                .containsEntry("USS Cerritos", 4L);

        astroData.forEach((craft, number) -> {
            System.out.println(number + " astronauts aboard " + craft);
            assertAll(
                    ()-> assertThat(number).isPositive(),
                    ()-> assertThat(craft).isNotBlank()
            );
        });

        // verify that the stubbed method was called
        verify(gateway).getResponse();

    }

    @Test
    void testAstrodata_usingFailedGateway() {
        // config mock
        when(gateway.getResponse()).thenThrow(
                new RuntimeException(new IOException("Network problems"))
        );

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()-> astroService.getAstroData())
                .withCauseInstanceOf(IOException.class)
                .withMessageContaining("Network problems");
    }

}