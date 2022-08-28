package de.mspark.example.jdaw.commands;

import static net.dv8tion.jda.api.Permission.ADMINISTRATOR;
import static net.dv8tion.jda.api.Permission.BAN_MEMBERS;
import static net.dv8tion.jda.api.Permission.KICK_MEMBERS;

import java.util.List;

import org.springframework.stereotype.Component;

import de.mspark.jdaw.cmdapi.TextCommand;
import de.mspark.jdaw.startup.JDAManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

@Component
public class BalanceTestCommand extends TextCommand {
    private JDAManager jdas;

    @Override
    public void onTrigger(Message msg, List<String> cmdArguments) {
        // first index is safe because "executableWithoutArgs" is false!
        switch (cmdArguments.get(0)) {
        case "run" -> msg.getChannel().sendMessage("Works!").submit();
        // even without balancing the whole command, its possible to execute actions on other bot instances.
        case "runelsewhere" -> jdas.getNextJDA().getTextChannelById(msg.getChannel().getId()).sendMessage("ok")
            .queue();
        default -> msg.reply("Unknown argument").submit();
        }
    }

    @Override
    public MessageEmbed commandHelpPage() {
        return new EmbedBuilder().setDescription("Hi!").build();
    }

    @Override
    public String trigger() {
        return "test";
    }

    @Override
    public String description() {
        return "Demonstration of different JDA execution";
    }

    @Override
    public Permission[] userGuildPermissions() {
        return new Permission[] { ADMINISTRATOR };
    }

    @Override
    public Permission[] botGuildPermissions() {
        return new Permission[] { KICK_MEMBERS, BAN_MEMBERS };
    }

}
