import java.net.URL;

import java.util.List;
import java.util.Map;

import java.io.InputStream;

public class App {
    public static void main(String[] args) throws Exception {
        Api api = new Api();
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> apiContents = jsonParser.parse(api.apiContent());

        StickerGen sticker = new StickerGen();
        for (Map<String, String> content : apiContents) {
            InputStream inputStream = new URL(content.get("url").replaceAll("(@+)(.*).jpg$", "$1.jpg")).openStream();
            sticker.create(inputStream, "output/" + content.get("title") + ".png");

            System.out.println(content.get("title"));
        }
    }
}
