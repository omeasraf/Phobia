package com.omeasraf.Phobia.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Say implements Commands{
    public void execute(MessageReceivedEvent event, String[] args){
        if (args.length > 0) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(String.join(" ", args)).queue();
        }
        else{
            event.getChannel().sendMessage("Invalid arguments!").queue();
        }
    }
}
