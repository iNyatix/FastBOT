package xyz.nyatix.fastbot.command.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import xyz.nyatix.fastbot.command.Command;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear", "[amount(2-100)]", Permission.MESSAGE_MANAGE);
    }

    @Override
    public void executeCommand(TextChannel textChannel, String[] args, Member commandAuthor, GuildMessageReceivedEvent event) {
        try {
            List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();

            event.getChannel().deleteMessages(messages).queue();

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("FastBOT | Clear")
                    .setColor(Color.GREEN)
                    .setDescription("Successfully deleted **" + messages.size() + "** messages!")
                    .setTimestamp(new Date().toInstant())
                    .setFooter("FastBOT");
            textChannel.sendMessage(embedBuilder.build()).queue(message -> message.delete().queueAfter(5, TimeUnit.SECONDS));
            messages.clear();
        } catch (Exception IGNORED) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("FastBOT | Error!")
                    .setColor(Color.RED)
                    .setDescription("Correct usage: **" + getCommand() + " " + getUsage() + "**")
                    .setTimestamp(new Date().toInstant())
                    .setFooter("FastBOT");
            textChannel.sendMessage(embedBuilder.build()).queue();
        }

    }
}
