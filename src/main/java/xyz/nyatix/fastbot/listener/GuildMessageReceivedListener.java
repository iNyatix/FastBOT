package xyz.nyatix.fastbot.listener;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.nyatix.fastbot.FastBOT;
import xyz.nyatix.fastbot.manager.CommandManager;

public class GuildMessageReceivedListener extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;

        String message = e.getMessage().getContentRaw();

        if (message.startsWith(FastBOT.getInstance().getConfigManager().getConfig().getPrefix())) {
            CommandManager.executeCommand(e.getChannel(), message.split(" "), e.getMember(), e);
        }

    }

}
