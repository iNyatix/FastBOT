package space.nyatix.fastbot.command.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import space.nyatix.fastbot.command.CommandInfo;
import space.nyatix.fastbot.object.Command;

import java.awt.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Nyatix
 * @created 04.09.2021 - 20:43
 */
@CommandInfo(name = "clear", usage = "[amount(2-100)]", permission = Permission.MESSAGE_MANAGE)
public class ClearCommand extends Command {
    @Override
    public void executeCommand(String[] args, TextChannel channel, Member sender, GuildMessageReceivedEvent event) {
        var embedBuilder = new EmbedBuilder();
        try {
            var messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();

            event.getChannel().deleteMessages(messages).queue();

            embedBuilder.setTitle("FastBOT | Clear")
                    .setColor(Color.GREEN)
                    .setDescription("Successfully deleted **" + messages.size() + "** messages!")
                    .setTimestamp(new Date().toInstant())
                    .setFooter("FastBOT");
            channel.sendMessage(embedBuilder.build()).queue(message -> message.delete().queueAfter(5, TimeUnit.SECONDS));
            messages.clear();
        } catch (Exception IGNORED) {
            embedBuilder.setTitle("FastBOT | Error!")
                    .setColor(Color.RED)
                    .setDescription(String.format("Correct usage: **%s %s**", getName(), getUsage()))
                    .setTimestamp(new Date().toInstant())
                    .setFooter("FastBOT");
            channel.sendMessage(embedBuilder.build()).queue();
        }
    }
}
