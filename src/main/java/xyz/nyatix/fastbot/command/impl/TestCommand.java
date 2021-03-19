package xyz.nyatix.fastbot.command.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import xyz.nyatix.fastbot.command.Command;

import java.awt.*;
import java.util.Date;

public class TestCommand extends Command {
    public TestCommand() {
        super("test", "", Permission.UNKNOWN);
    }

    @Override
    public void executeCommand(TextChannel textChannel, String[] args, Member commandAuthor, GuildMessageReceivedEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("FastBOT | Test")
                .setColor(Color.GREEN)
                .setDescription("This is example command...")
                .setTimestamp(new Date().toInstant())
                .setFooter("FastBOT");
        textChannel.sendMessage(embedBuilder.build()).queue();
    }
}
