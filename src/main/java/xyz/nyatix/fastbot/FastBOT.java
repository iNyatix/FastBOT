package xyz.nyatix.fastbot;

import lombok.Getter;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDABuilder;
import xyz.nyatix.fastbot.manager.ConfigManager;

import java.io.File;

public class FastBOT {

    @Getter
    public static FastBOT instance;

    private final ConfigManager configManager;


    public FastBOT() {
        instance = this;
        configManager = new ConfigManager(new File("FastBOT/config.json"));
        configManager.loadConfig();
    }

    @SneakyThrows
    public void startBOT() {

        System.out.println("");
        System.out.println("» Loading FastBOT... «");
        System.out.println("» Author: Nyatix «");
        System.out.println("» Source code: https://github.com/iNyatix/FastBOT «");
        System.out.println("");
        long start = System.currentTimeMillis();
        System.out.println("» Starting bot...");
        JDABuilder.createDefault(configManager.getConfig().getToken()).build();
        System.out.println("» Successfully started bot in time " + (System.currentTimeMillis() - start) + "MS");
    }


}
