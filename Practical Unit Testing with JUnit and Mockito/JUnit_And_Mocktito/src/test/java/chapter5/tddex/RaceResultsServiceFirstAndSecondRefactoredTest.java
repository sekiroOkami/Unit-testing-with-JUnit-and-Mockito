package chapter5.tddex;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RaceResultsServiceFirstAndSecondRefactoredTest {
    private RaceResultsService raceResultsService = new RaceResultsService();
    private Message message = mock(Message.class);
    private Client clientA = mock(Client.class, "ClientA");
    private Client clientB = mock(Client.class, "ClientB");



    @Test
    void notSubscribedClientShouldNotReceiveMessage() {
        raceResultsService.send(message);

        verify(clientA, never()).receive(message);
        verify(clientB, never()).receive(message);
    }

    @Test
    void subscribedClientShouldReceiveMessage() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.send(message);
        verify(clientA).receive(message);
    }

    @Test
    void allSubscribedClientShouldReceiveMessage() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.addSubscriber(clientB);
        raceResultsService.send(message);
        verify(clientA).receive(message);
        verify(clientB).receive(message);
    }

    @Test
    void shouldSendOnlyOneMessageToMultiSubscriber() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.addSubscriber(clientA);
        raceResultsService.send(message);
        verify(clientA).receive(message);
    }

    @Test
    void unsubscribedClientShouldNotReceiveMessages() {
        raceResultsService.addSubscriber(clientA);
        raceResultsService.removeSubscriber(clientA);

        raceResultsService.send(message);
        verify(clientA, never()).receive(message);
    }


}
