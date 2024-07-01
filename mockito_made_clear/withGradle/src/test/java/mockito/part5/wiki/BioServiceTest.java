package mockito.part5.wiki;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

public class BioServiceTest {

    @Test // integration test
    void checkBios() {
        BioService service = new BioService("Anita Borg", "Ada Lovelace",
                "Grace Hopper", "Barbara Liskov");

        assertThat(service.getBios()).hasSize(4);
    }

    @Test
    void testBioServiceWithMocks() {
        BioService service = new BioService("Anita Borg", "Ada Lovelace",
                "Grace Hopper", "Barbara Liskov");

        // Use mockStatic in twr
        try (MockedStatic<WikiUtil> mockedStatic = mockStatic(WikiUtil.class)) {

            mockedStatic.when(()-> WikiUtil.getWikipediaExtract(anyString()))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            assertThat(service.getBios()).hasSize(4);

            mockedStatic.verify(()-> WikiUtil.getWikipediaExtract(anyString()),
                    times(4));
        }
    }
}
