package xyz.nyatix.fastbot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import xyz.nyatix.fastbot.FastBOT;
import xyz.nyatix.fastbot.command.impl.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CommandManager {

    public static List<Command> commandsList = new ArrayList<>();

    public List<Command> getCommands() {
        return commandsList;
    }

    public void addCommand(Command command) {
        commandsList.add(command);
    }

    public void init() {
        addCommand(new TestCommand());
        addCommand(new BanCommand());
        addCommand(new KickCommand());
        addCommand(new ClearCommand());
    }

    public static void executeCommand(TextChannel textChannel, String[] args, Member commandAuthor, GuildMessageReceivedEvent event) {
        Optional<Command> commandOptional = commandsList.stream().filter(command -> (FastBOT.getInstance().getConfigManager().getConfig().getPrefix() + command.getCommand()).equalsIgnoreCase(args[0])).findFirst();

        if (!commandOptional.isPresent()) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("FastBOT | Error!")
                    .setColor(Color.RED)
                    .setDescription("Command not found!")
                    .setTimestamp(new Date().toInstant())
                    .setFooter("FastBOT");
            textChannel.sendMessage(embedBuilder.build()).queue();
            return;
        }

        Command command = commandOptional.get();

        if (!commandAuthor.hasPermission(command.getPermission())) {
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("FastBOT | Error!")
                    .setColor(Color.RED)
                    .setDescription("You don't have permissions to use this command!")
                    .setTimestamp(new Date().toInstant())
                    .setFooter("FastBOT");
            textChannel.sendMessage(embedBuilder.build()).queue();
            return;
        }

        String[] cmdUsage = command.getUsage().split(" ");

        if (args.length <= cmdUsage.length && !(command.getUsage().length() == 0)) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("FastBOT | Error!")
                    .setColor(Color.RED)
                    .setDescription("Correct usage: **" + FastBOT.getInstance().getConfigManager().getConfig().getPrefix() + command.getCommand() + " " + command.getUsage() + "**")
                    .setTimestamp(new Date().toInstant())
                    .setFooter("FastBOT");
            textChannel.sendMessage(embedBuilder.build()).queue();
            return;
        }

        command.executeCommand(textChannel, args, commandAuthor, event);
    }
}
