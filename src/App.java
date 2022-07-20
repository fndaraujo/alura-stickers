import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.List;
import java.util.Map;

import java.io.InputStream;

public class App {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpReq = HttpRequest.newBuilder(URI.create(Api.apiConnection()))
            .GET()
            .build();

        HttpResponse<String> httpRes = httpClient.send(
            httpReq,
            BodyHandlers.ofString()
        );

        JsonParser jsonParser = new JsonParser();

        List<Map<String, String>> moviesList = jsonParser.parse(httpRes.body());

        StickerGen sticker = new StickerGen();
        for (Map<String, String> movie : moviesList) {
            InputStream inputStream = new URL(movie.get("image")).openStream();
            sticker.create(inputStream, movie.get("title") + ".png");

            System.out.println(movie.get("title"));
        }
    }
}
