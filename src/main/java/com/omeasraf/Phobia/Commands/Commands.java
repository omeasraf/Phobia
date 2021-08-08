package com.omeasraf.Phobia.Commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Commands {
    String description = "Command Info";
    /**
     * @param event Message Received Event sent by JDA
     * @param args  Whitespace seperated raw message texts in an array
     * @see MessageReceivedEvent
     */
    void execute(MessageReceivedEvent event, String[] args);

    /**
     * @param event Slash Command Event sent by JDA
     * @see MessageReceivedEvent
     */
    void execute(SlashCommandEvent event);
}
