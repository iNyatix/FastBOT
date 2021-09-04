package space.nyatix.fastbot.object;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * @author Nyatix
 * @created 28.08.2021 - 20:45
 */
public abstract class Command {

    private String name, usage;
    private Permission permission;

    public abstract void executeCommand(String[] args, TextChannel channel, Member sender, GuildMessageReceivedEvent event);

    public String getUsage() {
        return usage;
    }

    public String getName() {
        return name;
    }

    public Permission getPermission() {
        return permission;
    }

    public Command setUsage(String usage) {
        this.usage = usage;
        return this;
    }

    public Command setName(String name) {
        this.name = name;
        return this;
    }

    public Command setPermission(Permission permission) {
        this.permission = permission;
        return this;
    }
}
