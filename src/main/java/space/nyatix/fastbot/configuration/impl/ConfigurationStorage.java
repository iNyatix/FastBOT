package space.nyatix.fastbot.configuration.impl;

/**
 * @author Nyatix
 * @created 28.08.2021 - 21:23
 */
public class ConfigurationStorage {

    public String token;
    public char prefix;

    public ConfigurationStorage(String token, char prefix) {
        this.token = token;
        this.prefix = prefix;
    }

    public String getToken() {
        return token;
    }

    public char getPrefix() {
        return prefix;
    }
}
