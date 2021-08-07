package com.omeasraf.Phobia.Commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public class Say implements Commands {
    public void execute(MessageReceivedEvent event, String[] args) {
        event.getMessage().delete().queue();
        if (args.length > 0) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(String.join(" ", args)).queue();
        } else {
            event.getChannel().sendMessage("Invalid arguments!").queue();
        }
    }

    public void execute(SlashCommandEvent event) {
        String message = Objects.requireNonNull(event.getOption("message")).getAsString();
        event.reply(message).queue();
    }
}
