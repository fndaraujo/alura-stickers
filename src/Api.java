import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.Properties;

public class Api {
    private static String configPath = "config.properties";

    public static String apiConnection() {
        try (InputStream inputStream = new FileInputStream(configPath)) {
            Properties configProperties = new Properties();
            configProperties.load(inputStream);
            return (configProperties.getProperty("url") + configProperties.getProperty("key"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    public static String apiContent() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpReq = HttpRequest.newBuilder(URI.create(apiConnection())).GET().build();
            HttpResponse<String> httpRes = httpClient.send(httpReq, BodyHandlers.ofString());
            return httpRes.body();
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
