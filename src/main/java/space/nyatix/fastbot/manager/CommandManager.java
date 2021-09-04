package space.nyatix.fastbot.manager;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.reflections.Reflections;
import space.nyatix.fastbot.FastBOT;
import space.nyatix.fastbot.command.CommandInfo;
import space.nyatix.fastbot.object.Command;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Nyatix
 * @created 28.08.2021 - 20:44
 */
public class CommandManager {

    private final List<Command> commands = new ArrayList<>();

    public void loadCommands() {
        for (Class<? extends Command> commandClass : new Reflections("space.nyatix.fastbot.command.impl").getSubTypesOf(Command.class)) {
            try {
                var command = commandClass.getDeclaredConstructor().newInstance();
                var commandInfo = command.getClass().getAnnotation(CommandInfo.class);
                command.setName(commandInfo.name())
                        .setPermission(commandInfo.permission())
                        .setUsage(commandInfo.usage());
                commands.add(command);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleCommand(String[] args, TextChannel channel, Member sender, GuildMessageReceivedEvent event) {
        var configuration = FastBOT.getInstance().getConfiguration().getConfigurationStorage();
        var commandOptional = commands.stream().filter(command -> (configuration.getPrefix() + command.getName()).equalsIgnoreCase(args[0])).findFirst();
        var date = new Date();
        var embedBuilder = new EmbedBuilder();

        if (commandOptional.isEmpty()) {
            embedBuilder.setTitle("FastBOT | Error!")
                    .setColor(Color.RED)
                    .setDescription("Command not found!")
                    .setTimestamp(date.toInstant())
                    .setFooter("FastBOT");
            channel.sendMessage(embedBuilder.build()).queue();
            return;
        }

        var command = commandOptional.get();

        if (!sender.hasPermission(command.getPermission())) {
            embedBuilder.setTitle("FastBOT | Error!")
                    .setColor(Color.RED)
                    .setDescription("You don't have enough permissions to use that command!")
                    .setTimestamp(date.toInstant())
                    .setFooter("FastBOT");
            channel.sendMessage(embedBuilder.build()).queue();
            return;
        }

        var commandUsage = command.getUsage().split(" ");

        if (args.length <= commandUsage.length && !(command.getUsage().length() == 0)) {
            embedBuilder.setTitle("FastBOT | Error!")
                    .setColor(Color.RED)
                    .setDescription(String.format("Correct usage: **%s %s**", configuration.getPrefix() + command.getName(), command.getUsage()))
                    .setTimestamp(date.toInstant())
                    .setFooter("FastBOT");
            channel.sendMessage(embedBuilder.build()).queue();
            return;
        }

        command.executeCommand(args, channel, sender, event);
    }

    public List<Command> getCommands() {
        return commands;
    }
}
