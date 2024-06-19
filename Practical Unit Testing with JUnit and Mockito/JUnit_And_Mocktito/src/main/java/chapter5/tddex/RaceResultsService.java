package chapter5.tddex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class RaceResultsService {
    private Collection<Client> clients = new HashSet<>();

    public void addSubscriber(Client client) {
        clients.add(client);
    }

    public void send(Message message) {
        clients.stream().forEach(client -> client.receive(message));
    }

    public void removeSubscriber(Client removeClient) {
        clients.remove(removeClient);
    }
}
