package chapter5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestDoubleTest {

    private final static String CLIENT_EMAIL = "client@email.com";
    private final static String MSG_CONTENT = "Important message";

    @Test
    void mockitoCreateInstancesOfRequestedTypes() {
        MailServer mailService = mock(MailServer.class);
        TemplateEngine templateEngine = mock(TemplateEngine.class);

        assertThat(mailService).isInstanceOf(MailServer.class);
        assertThat(templateEngine).isInstanceOf(TemplateEngine.class);
        Template template = mock(Template.class);
        Client client = mock(Client.class);

        Messenger sut = new Messenger(templateEngine, mailService);

        // config stubbing
        when(templateEngine.preparedMessage(template, client)).thenReturn(MSG_CONTENT);
        when(client.getEmail()).thenReturn(CLIENT_EMAIL);

        sut.sendMessage(client, template);
        verify(mailService).send(CLIENT_EMAIL, MSG_CONTENT);
    }



}
