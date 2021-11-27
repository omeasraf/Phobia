package com.omeasraf.Phobia.Commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class FlipCoin implements Commands{

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendTyping().queue();
        event.getMessage().reply(getOutcome()).queue();
    }

    private String getOutcome(){
        int random = new Random().nextInt(1000);
        return random % 2 == 0 ? "Heads" : "Tails";
    }

    @Override
    public void execute(SlashCommandEvent event) {
        event.reply(getOutcome()).queue();
    }
}
