package chapter5;

public class Messenger {
    private TemplateEngine templateEngine;
    private MailServer mailServer;

    public Messenger(TemplateEngine templateEngine, MailServer mailServer) {
        this.templateEngine = templateEngine;
        this.mailServer = mailServer;
    }

    public void sendMessage(Client client, Template template) {
        String  msgContent =
                templateEngine.preparedMessage(template, client);
        mailServer.send(client.getEmail(), msgContent);
    }
}
