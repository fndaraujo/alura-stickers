import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class ContentsImdb implements ContentsApi {
    public List<Content> getContents(String json) {
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> apiContents = jsonParser.parse(json);
        List<Content> contents = new ArrayList<>();
        for (Map<String, String> attributes : apiContents) {
            String title = attributes.get("title");
            String urlImage = attributes.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            Content content = new Content(title, urlImage);
            contents.add(content);
        }
        return contents;
    }
}
