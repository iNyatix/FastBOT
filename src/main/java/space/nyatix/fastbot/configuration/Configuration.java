package space.nyatix.fastbot.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import space.nyatix.fastbot.configuration.impl.ConfigurationStorage;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Nyatix
 * @created 28.08.2021 - 21:17
 */
public class Configuration {

    private final File configurationFile = new File("FastBOT", "config.json");
    private final Gson gson = new GsonBuilder().disableHtmlEscaping()
            .registerTypeAdapter(
                    String.class,
                    (JsonDeserializer<String>) (jsonElement, type, jsonDeserializationContext) ->
                            new String(jsonElement.getAsString().getBytes(), StandardCharsets.UTF_8)
            )
            .setPrettyPrinting()
            .create();

    private ConfigurationStorage configurationStorage;

    public void createConfiguration() {
        try {
            if (!configurationFile.createNewFile()) return;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(configurationFile))) {
                configurationStorage = new ConfigurationStorage(
                        "token",
                        '$'
                );
                gson.toJson(configurationStorage, writer);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfiguration() {
        createConfiguration();
        try (BufferedReader reader = new BufferedReader(new FileReader(configurationFile))) {
            configurationStorage = gson.fromJson(reader, ConfigurationStorage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigurationStorage getConfigurationStorage() {
        return configurationStorage;
    }
}
