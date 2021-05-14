package xyz.nyatix.fastbot;

import lombok.Getter;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import xyz.nyatix.fastbot.manager.CommandManager;
import xyz.nyatix.fastbot.listener.GuildMessageReceivedListener;
import xyz.nyatix.fastbot.manager.ConfigManager;

import java.io.File;

@Getter
public class FastBOT {

    @Getter
    public static FastBOT instance;

    private final ConfigManager configManager;
    private final CommandManager commandManager;

    public FastBOT() {
        instance = this;
        configManager = new ConfigManager(new File("FastBOT/config.json"));
        commandManager = new CommandManager();
    }

    @SneakyThrows
    public void startBOT() {

        System.out.println();
        System.out.println("» Loading FastBOT... «");
        System.out.println("» Author: Nyatix «");
        System.out.println("» Source code: https://github.com/iNyatix/FastBOT «");
        System.out.println();
        long start = System.currentTimeMillis();
        System.out.println("» Loading commands...");
        commandManager.loadCommands();
        System.out.println("» Loaded " + commandManager.getCommands().size() + " commands!");
        System.out.println("» Starting bot...");
        JDABuilder.createDefault(configManager.getConfig().getToken())
                .addEventListeners(new GuildMessageReceivedListener())
                .setActivity(Activity.watching("Source code: https://github.com/iNyatix/FastBOT"))
                .build();
        System.out.println("» Successfully started bot in time " + (System.currentTimeMillis() - start) + "MS");
    }


}
