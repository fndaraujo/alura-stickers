import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.List;
import java.util.Map;

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

        JsonParser jsonParser = new JsonParser();

        List<Map<String, String>> moviesList = jsonParser.parse(httpRes.body());

        for (Map<String, String> movie : moviesList) {
            String ratingStars = "";
            for (int i = 0; i < (int)Double.parseDouble(movie.get("imDbRating")); ++i) {
                ratingStars+= "*";
            }
            System.out.println(
                movie.get("title")
                + " ["
                + movie.get("image")
                + "] "
                + movie.get("imDbRating")
                + " "
                + ratingStars
            );
            ratingStars = "";
        }
    }
}
