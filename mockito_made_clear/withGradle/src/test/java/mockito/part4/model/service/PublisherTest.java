package mockito.part4.model.service;

import mockito.part4.model.pubsub.Publisher;
import mockito.part4.model.pubsub.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class PublisherTest {
    private final Publisher pub = new Publisher();
    private final Subscriber sub1 = mock(Subscriber.class);
    private final Subscriber sub2 = mock(Subscriber.class);

    @BeforeEach
    void setUp() {
        pub.subscribe(sub1);
        pub.subscribe(sub2);
    }

    @Test
    void publisherSendsMessageToAllSubscribers() {
        pub.send("Hello");

        verify(sub1).onNext("Hello");
        verify(sub2).onNext("Hello");
    }

    @Test
    void testSendInOrder() {
        pub.send("Hello");

        InOrder inOrder = Mockito.inOrder(sub1, sub2);
        inOrder.verify(sub1).onNext("Hello");
        inOrder.verify(sub2).onNext("Hello");
    }

    @Test
    void publisherSendsMessageWithAPattern() {
        pub.send("Message 1");
        pub.send("Message 2");

        // check for any String
        verify(sub1, times(2)).onNext(anyString());
        verify(sub2, times(2)).onNext(anyString());

        // alternatively, check for a specific pattern
        verify(sub1, times(2)).onNext(argThat(s-> s.matches("Message \\d")));
        verify(sub2, times(2)).onNext(argThat(s-> s.matches("Message \\d")));

    }

    @Test
    void handleMisbehavingSubscribers() {
        // sub1 throws an exception
        // noNext() return void , have to use doThrow
        doThrow(RuntimeException.class).when(sub1).onNext("hello");

        pub.send("message 1");
        pub.send("message 2");

        // both sub still received the messages
        verify(sub1, times(2)).onNext(anyString());
        verify(sub2, times(2)).onNext(anyString());
    }
}
