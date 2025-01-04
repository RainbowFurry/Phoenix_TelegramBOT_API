package net.rainbowfurry.phoenixtelegrambotapi.manager;

import java.io.*;
import java.util.Properties;

public class ConfigManager {

    private final Properties properties = new Properties();

    public ConfigManager() {
        try {

            File dir = new File("Configs");
            File file = new File(dir, "config.txt");
            if (!dir.exists())
                dir.mkdirs();
            if (!file.exists() &&
                    file.createNewFile())
                try {
                    OutputStream outputStream = new FileOutputStream(file);
                    this.properties.setProperty("token", "YOUR_TOKEN");
                    this.properties.setProperty("name", "NAME");
                    this.properties.setProperty("chatID", "ChatID");
                    this.properties.store(outputStream, (String) null);
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            InputStream inputStream = new FileInputStream(file);
            this.properties.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String getKey(String key) {
        return this.properties.getProperty(key);
    }

}
