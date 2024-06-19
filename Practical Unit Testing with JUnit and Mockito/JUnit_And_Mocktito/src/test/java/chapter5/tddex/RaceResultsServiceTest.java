package chapter5.tddex;
import chapter5.tddex.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RaceResultsServiceTest {

    @Test
    void subscribedClientShouldReceiveMessage() {
        RaceResultsService raceResult = new RaceResultsService();
        Client client = mock(Client.class);
        Message message = mock(Message.class);

        raceResult.addSubscriber(client);
        raceResult.send(message);

        verify(client).receive(message);
    }

}