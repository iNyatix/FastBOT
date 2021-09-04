package space.nyatix.fastbot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import space.nyatix.fastbot.configuration.Configuration;
import space.nyatix.fastbot.listener.GuildMessageReceivedListener;
import space.nyatix.fastbot.manager.CommandManager;
import space.nyatix.fastbot.util.FileUtil;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.Collections;

/**
 * @author Nyatix
 * @created 28.08.2021 - 20:43
 */
public class FastBOT {

    private static FastBOT instance;

    private final CommandManager commandManager = new CommandManager();
    private final Configuration configuration = new Configuration();
    private final File botFiles = new File("FastBOT");

    public FastBOT() {
        instance = this;
    }

    public void start() throws LoginException {
        System.out.println(
                """
                ╔                                                     ╗
                               Loading FastBOT 2.0
                                 Author: Nyatix
                      Source code:
                               https://github.com/iNyatix/FastBOT
                ╚                                                     ╝
                """
        );
        var start = System.currentTimeMillis();
        System.out.println("» Creating missing files...");
        FileUtil.createFiles();
        System.out.println("» Created missing files!");
        System.out.println("» Loading configuration...");
        configuration.loadConfiguration();
        System.out.println("» Loaded bot configuration!");
        System.out.println("» Loading commands...");
        commandManager.loadCommands();
        System.out.printf("» Loaded %s commands!%n", commandManager.getCommands().size());
        System.out.println("» Starting bot...");
        JDABuilder.createDefault(configuration.getConfigurationStorage().getToken())
                .addEventListeners(new GuildMessageReceivedListener())
                .setActivity(Activity.watching("Source code: https://github.com/iNyatix/FastBOT"))
                .build();
        System.out.printf("» Successfully started bot in time %s MS!%n", (System.currentTimeMillis() - start));

    }

    public File getBotFiles() {
        return botFiles;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public static FastBOT getInstance() {
        return instance;
    }
}
