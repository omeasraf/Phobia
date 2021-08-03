package com.omeasraf.Phobia.Commands;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Objects;

public class Info implements Commands{
    public void execute(MessageReceivedEvent event, String[] args){
        System.out.println("Info");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Server Info");
        embed.setAuthor(event.getAuthor().getName());
        embed.setColor(Color.RED);
        embed.addField("User Count", String.valueOf(getUsersInGuild(event.getGuild())), true);
        embed.addField("Owner", Objects.requireNonNull(event.getGuild().getOwner()).getEffectiveName() , false);
        event.getChannel().sendMessage(embed.build()).queue();
    }
    private long getUsersInGuild(Guild guild) {
        return guild.getMembers().stream().filter(member -> !member.getUser().isBot()).count();
    }
}
