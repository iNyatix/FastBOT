package xyz.nyatix.fastbot.command;

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

    public abstract void executeCommand(final TextChannel textChannel, final String[] args, final Member commandAuthor, GuildMessageReceivedEvent event);

}
