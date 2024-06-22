package mockito.part1.astro.model;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.google.gson.Gson;

public class AstroGateway implements Gateway<AstroResponse> {

    private static final String DEFAULT_URL = "http://api.open-notify.org/";
    private final String url;

    public AstroGateway(String url) {
        this.url = url;
    }

    public AstroGateway() {
        this(DEFAULT_URL);
    }

    @Override
    public AstroResponse getResponse() {

        // this client can be used to send HTTP requests and receive responses
        HttpClient client = HttpClient.newHttpClient();

        // Build an HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "astros.json")) // Set the URI of the request
                .timeout(Duration.ofSeconds(2)) // Set request timeOut
                .build();

        try {

            // Send the HTTP request and receive the response
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response body into an AstroResponse obj
            return new Gson().fromJson(response.body(), AstroResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
