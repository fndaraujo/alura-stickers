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
}
