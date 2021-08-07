package com.omeasraf.Phobia;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import javax.security.auth.login.LoginException;

public class App {
    public static Dotenv dotenv;
    public static JDA jda;
    public static void main(String[] args) throws LoginException {
        dotenv = Dotenv.load();
        jda = JDABuilder.createDefault(dotenv.get("DISCORD_TOKEN")).build();
        setStatus();
        registerSlashCommands();
        jda.addEventListener(new Handler());
        System.out.println("\u001B[32m Phobia is listening to new commands!" );
    }



    public static void setStatus(){
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("You!"));
    }

    public static void registerSlashCommands(){
        jda.upsertCommand(new CommandData("ping", "Responds with the ping!")).queue();
        jda.upsertCommand(new CommandData("info", "Responds with the server info!")).queue();
        jda.upsertCommand(new CommandData("say", "Responds with the server info!").addOptions(new OptionData(
                OptionType.STRING,
                "message",
                "Repeats whatever is typed",
                true
        ))).queue();
    }
}
