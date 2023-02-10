package me.sydneynguyen;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class ConfessionsBot extends ListenerAdapter {
    public static void main(String[] args) throws InterruptedException {

        // Creates the confessions bot
        JDA bot = JDABuilder.createDefault("MTA2ODYzOTI0MjU0NjMzNTc1NA.GxDPo9.MmlgNXzc-b3e41NCdGhGRJpzpkZXk465ABxX78")
                .setActivity(Activity.listening("your sins"))
                .addEventListeners(new Commands())
                .addEventListeners(new EventListener())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build().awaitReady();

        // Retrieves server ID
        Guild guild = bot.getGuildById("249586456090247178");

        // Creates the confession option with a description
        // to do: add optional attachment option
        // figure out how to keep track of the # of confessions
        // reporting capabilities
        if (guild != null) {
            guild.upsertCommand("confess", "Submit an anonymous confession")
                    .addOption(OptionType.STRING, "confession", "your confession here", true)
                    .addOption(OptionType.ATTACHMENT, "attachments", "attach an image or GIF", false)
                    .addOption(OptionType.CHANNEL, "channels", "choose a channel to submit your confession in", false)
                    .queue();
            guild.upsertCommand("wordcount", "counts number of words in the message")
                    .addOption(OptionType.STRING, "words", "insert your sentence or paragraph here", true)
                    .queue();
        }

    }

}
