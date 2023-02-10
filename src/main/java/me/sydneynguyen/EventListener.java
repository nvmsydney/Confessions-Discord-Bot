package me.sydneynguyen;


import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Replies with "NO U" if the message contains a version of "Shut up"
        if (!(event.getAuthor().isBot())) {
            String message = event.getMessage().getContentRaw();
            if (message.equalsIgnoreCase("STFU")
                    || message.equalsIgnoreCase("SHUT THE FUCK UP")
                    || message.equalsIgnoreCase("SHUT UP")) {
                event.getChannel().sendMessage("NO U").queue();
            }
        }
    }
}
