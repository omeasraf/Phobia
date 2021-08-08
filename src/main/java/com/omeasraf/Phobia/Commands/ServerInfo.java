package com.omeasraf.Phobia.Commands;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ServerInfo implements Commands {
    public static String description = "Responds with the server info!";
    public void execute(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessageEmbeds(buildEmbed(event.getGuild()).build()).queue();
    }

    public void execute(SlashCommandEvent event) {
        event.replyEmbeds(buildEmbed(Objects.requireNonNull(event.getGuild())).build()).queue();
    }


    private EmbedBuilder buildEmbed(Guild guild) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String icon, name, owner, created;
        int txtChannel, voiceChannels;

        icon = guild.getIconUrl();
        name = guild.getName();
        owner = Objects.requireNonNull(guild.getOwner()).getEffectiveName();
        txtChannel = guild.getTextChannels().size();
        voiceChannels = guild.getVoiceChannels().size();
        created = fmt.format(guild.getTimeCreated());
        var members = guild.getMembers();

        EmbedBuilder embed = new EmbedBuilder()
                .setAuthor(name, null, null).setColor(Color.yellow).setThumbnail(icon)
                .setFooter(String.format("ID: %s | Server Created • %s", guild.getId(), created), null);
        embed.addField("Owner", owner, true);
        embed.addField("Channel Categories", String.valueOf(guild.getCategories().size()), true);
        embed.addField("Text Channels", String.valueOf(txtChannel), true);
        embed.addField("Voice Channels", String.valueOf(voiceChannels), true);
        embed.addField("Members", String.valueOf(members.size()), true);
        embed.addField("Roles", String.valueOf(guild.getRoles().size()), true);
        embed.addField("Role List", String.join(" | ", guild.getRoles().stream().map(Role::getName).toList()), false);


        return embed;
    }

    private long getUsersInGuild(Guild guild) {
        return guild.getMembers().stream().filter(member -> !member.getUser().isBot()).count();
    }
}
