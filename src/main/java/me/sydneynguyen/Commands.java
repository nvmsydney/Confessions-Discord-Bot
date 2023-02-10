package me.sydneynguyen;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class Commands extends ListenerAdapter {
    private int confessionNumber = 0;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("confess")) {
            // Defers the reply
            event.deferReply(true).queue();
            // Acknowledges command
            event.getHook().sendMessage("Your confession has successfully sent!").queue();

            // Gives "confession" as an option for /confess command
            OptionMapping confessionOption = event.getOption("confession");
            // If the confession input is null then return
            if (confessionOption == null) {
                event.reply("A confession was not provided. Please try again.").queue();
                return;
            }

            // Create EmbedBuilder object
            EmbedBuilder eb = new EmbedBuilder();

            // Customizes title, footer, and colors
            eb.setTitle("Anonymous Confession (#" + ++confessionNumber + ")");
            eb.setFooter("any bugs can be sent to @sydney#6966!");
            eb.setColor(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));

            // Turns option into a string
            String confession = confessionOption.getAsString();
            // Confession is inputted as a description
            eb.setDescription(confession);

            // Handles attachments if the user adds them
            OptionMapping attachmentOption = event.getOption("attachments");
            if (attachmentOption != null) {
                eb.setImage(attachmentOption.getAsAttachment().getUrl());
            }

            // Builds embed and sends the message
            event.getChannel().sendMessageEmbeds(eb.build()).queue();
        }
    }
}