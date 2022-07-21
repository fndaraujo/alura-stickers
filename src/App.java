import java.net.URL;

import java.util.List;
import java.util.Map;

import java.io.InputStream;

public class App {
    public static void main(String[] args) throws Exception {
        Api api = new Api();
        //ContentsApi apiContents = new ContentsImdb();
        ContentsApi apiContents = new ContentsNasa();
        List<Content> contents = apiContents.getContents(api.apiContent());

        StickerGen sticker = new StickerGen();
        for (Content content : contents) {
            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            sticker.create(inputStream, "output/" + content.getTitle() + ".png");

            System.out.println(content.getTitle());
        }
    }
}
