package xyz.nyatix.fastbot.object;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

@RequiredArgsConstructor
@Getter
public abstract class Command {

    private final String command;
    private final String usage;
    private final Permission permission;

    public abstract void executeCommand(TextChannel textChannel, String[] args, Member commandAuthor, GuildMessageReceivedEvent event);

}
