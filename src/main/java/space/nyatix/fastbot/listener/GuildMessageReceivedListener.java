package space.nyatix.fastbot.listener;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import space.nyatix.fastbot.FastBOT;

/**
 * @author Nyatix
 * @created 28.08.2021 - 21:37
 */
public class GuildMessageReceivedListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        var message = event.getMessage().getContentRaw();
        if (message.startsWith(String.valueOf(FastBOT.getInstance().getConfiguration().getConfigurationStorage().getPrefix()))) {
            FastBOT.getInstance().getCommandManager().handleCommand(message.split(" "), event.getChannel(), event.getMember(), event);
        }
    }
}
