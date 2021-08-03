package com.omeasraf.Phobia;

import com.omeasraf.Phobia.Commands.Commands;
import com.omeasraf.Phobia.Commands.Info;
import com.omeasraf.Phobia.Commands.Say;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.HashMap;

public class Handler extends ListenerAdapter {
    public HashMap<String, Commands> commands = new HashMap<>();

    Handler() {
        commands.put("say", new Say());
        commands.put("info", new Info());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].toLowerCase().contains(Config.prefix.toLowerCase())) {
            String command = args[0].toLowerCase().split(Config.prefix.toLowerCase())[1];
            Commands exec = commands.get(command);
            if (exec != null) {
                exec.execute(event, args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[]{});
            }
        }
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        System.out.println(event.getName());
        if (!event.getName().equals("ping")) return; // make sure we handle the right command
        long time = System.currentTimeMillis();
        event.reply("Pong!").setEphemeral(true) // reply or acknowledge
                .flatMap(v ->
                        event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
                ).queue(); // Queue both reply and edit
    }
}
