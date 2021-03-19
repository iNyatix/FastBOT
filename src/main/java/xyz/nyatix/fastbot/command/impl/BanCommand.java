package xyz.nyatix.fastbot.command.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import xyz.nyatix.fastbot.command.Command;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class BanCommand extends Command {
    public BanCommand() {
        super("ban", "[member] [reason]", Permission.BAN_MEMBERS);
    }

    @Override
    public void executeCommand(TextChannel textChannel, String[] args, Member commandAuthor, GuildMessageReceivedEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        Member selfMember = event.getGuild().getSelfMember();

        List<Member> mentionedMembers = event.getMessage().getMentionedMembers();

        Member target = mentionedMembers.get(0);

        StringBuilder reason = new StringBuilder(" ");

        for (int i = 2; i < args.length; i++) {
            reason.append(args[i]).append(" ");
        }

        Date date = new Date();

        if (!selfMember.hasPermission(Permission.BAN_MEMBERS) || !selfMember.canInteract(target)) {
             embedBuilder.setTitle("FastBOT | Error!")
                     .setColor(Color.RED)
                     .setDescription("I'm don't have permissions to ban the specified person.")
                     .setTimestamp(date.toInstant())
                     .setFooter("FastBOT");
             textChannel.sendMessage(embedBuilder.build()).queue();
             return;
        }

        embedBuilder.setTitle("FastBOT | Ban")
                .setColor(Color.GREEN)
                .setDescription(String.format("Successfully banned member!\n**Admin: **%s**\nBanned member: **%s**\nReason: **%s", commandAuthor.getAsMention(), target.getAsMention(), reason))
                .setTimestamp(date.toInstant())
                .setFooter("FastBOT");
        textChannel.sendMessage(embedBuilder.build()).queue();

        event.getGuild().ban(target, 1).queue();
    }
}
