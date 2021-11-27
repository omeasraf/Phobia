package com.omeasraf.Phobia.Commands;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DoesNotExist implements Commands{

    public void execute(MessageReceivedEvent event, String[] args) {
        // Update to include actual network image rather than a url
        event.getMessage().reply("https://thispersondoesnotexist.com/image").queue();
    }

    public void execute(SlashCommandEvent event) {

    }

}
