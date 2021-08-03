package com.omeasraf.Phobia.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Commands {
    void execute(MessageReceivedEvent event, String[] args);
}
