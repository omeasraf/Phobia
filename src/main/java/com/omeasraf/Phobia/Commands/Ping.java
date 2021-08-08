package com.omeasraf.Phobia.Commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping implements Commands {
    public static String description = "Responds with the ping time!";
    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        long time = System.currentTimeMillis();
        var msg = event.getChannel().sendMessage("Pong!");
        msg.queue(message -> {
            message.editMessage(String.format("Pong: %d ms", System.currentTimeMillis() - time))
                    .queue();
        });
    }

    public void execute(SlashCommandEvent event) {
        long time = System.currentTimeMillis();
        event.reply("Pong!").setEphemeral(true)
                .flatMap(v ->
                        event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time)
                ).queue();
    }
}
