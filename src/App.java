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

        List<Map<String, String>> apiContents = jsonParser.parse(httpRes.body());

        StickerGen sticker = new StickerGen();
        for (Map<String, String> content : apiContents) {
            InputStream inputStream = new URL(content.get("url").replaceAll("(@+)(.*).jpg$", "$1.jpg")).openStream();
            sticker.create(inputStream, "output/" + content.get("title") + ".png");

            System.out.println(content.get("title"));
        }
    }
}
