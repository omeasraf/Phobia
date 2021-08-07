package com.omeasraf.Phobia.Commands;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Objects;

public class Info implements Commands{
    public void execute(MessageReceivedEvent event, String[] args){
        event.getChannel().sendMessage(buildEmbed(event.getGuild()).build()).queue();
    }

    public void execute(SlashCommandEvent event) {
        event.replyEmbeds(buildEmbed(event.getGuild()).build()).queue();
    }


    private  EmbedBuilder  buildEmbed(Guild event){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Server Info");
        embed.setAuthor(Objects.requireNonNull(event).getName());
        embed.setColor(Color.RED);
        embed.addField("User Count", String.valueOf(getUsersInGuild(Objects.requireNonNull(event))), true);
        embed.addField("Owner", Objects.requireNonNull(event.getOwner()).getEffectiveName() , false);

        return embed;
    }
    private long getUsersInGuild(Guild guild) {
        return guild.getMembers().stream().filter(member -> !member.getUser().isBot()).count();
    }
}
