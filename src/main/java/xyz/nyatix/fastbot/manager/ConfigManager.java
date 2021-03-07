package xyz.nyatix.fastbot.manager;

import lombok.Getter;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import xyz.nyatix.fastbot.object.Config;

import java.io.File;
import java.io.FileReader;

public class ConfigManager {

    @Getter
    private final Config config;
    private final File file;

    public ConfigManager(File file) {
        this.file = file;
        this.config = new Config();
    }

    @SneakyThrows
    public void loadConfig() {
        JSONParser parser = new JSONParser();
        Object parserObj = parser.parse(new FileReader(file));
        JSONObject jsonObject = (JSONObject) parserObj;
        JSONObject settings = (JSONObject) jsonObject.get("settings");

        getConfig().setToken((String) settings.get("token"));
    }

}