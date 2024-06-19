package chapter5.tddex;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RaceResultsServiceFirstAndSecondTest {

    @Test
    void subscribedClientShouldReceiveMessage() {
        RaceResultsService raceResult = new RaceResultsService();
        Client client = mock(Client.class);
        Message message = mock(Message.class);

        raceResult.addSubscriber(client);
        raceResult.send(message);

        verify(client).receive(message);
    }

    @Test
    void messageShouldBeSentToAllSubscribedClients() {
        RaceResultsService raceResult = new RaceResultsService();
        Client clientA = mock(Client.class, "ClientA");
        Client clientB = mock(Client.class, "ClientB");
        Message message = mock(Message.class);

        raceResult.addSubscriber(clientA);
        raceResult.addSubscriber(clientB);
        raceResult.send(message);
        verify(clientA).receive(message);
        verify(clientB).receive(message);


    }

}