import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpReq = HttpRequest.newBuilder(URI.create(ApiAccess.getApiAccess()))
            .GET()
            .build();

        HttpResponse<String> httpRes = httpClient.send(
            httpReq,
            BodyHandlers.ofString()
        );

        String body = httpRes.body();

        System.out.println(body);
    }
}
