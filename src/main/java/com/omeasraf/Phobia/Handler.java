package com.omeasraf.Phobia;

import com.omeasraf.Phobia.Commands.Commands;
import com.omeasraf.Phobia.Commands.ServerInfo;
import com.omeasraf.Phobia.Commands.Ping;
import com.omeasraf.Phobia.Commands.Say;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

public class Handler extends ListenerAdapter {
    public static HashMap<String, Commands> commands = new HashMap<>();

    Handler() {
        commands.put("say", new Say());
        commands.put("serverinfo", new ServerInfo());
        commands.put("ping", new Ping());
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        try {
            String[] args = event.getMessage().getContentRaw().split(" ");
            if (args[0].length() > Config.prefix.length() && args[0].toLowerCase().startsWith(Config.prefix.toLowerCase())) {
                String command = args[0].toLowerCase().replace(Config.prefix.toLowerCase(), "");
                Commands exec = commands.get(command);
                if (exec != null) {
                    exec.execute(event, args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[]{});
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            System.out.println("Message: " + e.getMessage());
        }
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        Commands exec = commands.get(event.getName().toLowerCase());
        if (exec == null) return;
        exec.execute(event);
    }
}
