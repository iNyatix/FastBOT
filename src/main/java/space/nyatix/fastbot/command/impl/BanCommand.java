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

/**
 * @author Nyatix
 * @created 04.09.2021 - 20:42
 */
@CommandInfo(name = "ban", usage = "[@member] [reason]", permission = Permission.BAN_MEMBERS)
public class BanCommand extends Command {
    @Override
    public void executeCommand(String[] args, TextChannel channel, Member sender, GuildMessageReceivedEvent event) {
        var embedBuilder = new EmbedBuilder();
        var selfMember = event.getGuild().getSelfMember();
        var mentionedMembers = event.getMessage().getMentionedMembers();
        var target = mentionedMembers.get(0);
        var reason = new StringBuilder(" ");
        var date = new Date();

        for (int i = 2; i < args.length; i++) {
            reason.append(args[i]).append(" ");
        }

        if (!selfMember.hasPermission(Permission.KICK_MEMBERS) || !selfMember.canInteract(target)) {
            embedBuilder.setTitle("FastBOT | Error!")
                    .setColor(Color.RED)
                    .setDescription("I'm need permissions to kick mentioned user!")
                    .setTimestamp(date.toInstant())
                    .setFooter("FastBOT");
            channel.sendMessage(embedBuilder.build()).queue();
            return;
        }

        embedBuilder.setTitle("FastBOT | Ban")
                .setColor(Color.GREEN)
                .setDescription(String.format("Successfully banned member!\n**Admin: **%s**\nBanned member: **%s**\nReason: **%s", sender.getAsMention(), target.getAsMention(), reason))
                .setTimestamp(date.toInstant())
                .setFooter("FastBOT");
        channel.sendMessage(embedBuilder.build()).queue();

        event.getGuild().ban(target, 1).queue();
    }
}
