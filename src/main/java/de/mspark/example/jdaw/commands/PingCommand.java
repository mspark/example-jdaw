package de.mspark.example.jdaw.commands;

import java.util.List;

import de.mspark.jdaw.command.Command;
import de.mspark.jdaw.command.CommandProperties;
import de.mspark.jdaw.jda.JDAManager;
import de.mspark.jdaw.jda.JDAWConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

@CommandProperties(trigger = "ping", description = "Command will answer you :)", executableWihtoutArgs = true)
public class PingCommand extends Command {

    public PingCommand(JDAWConfig conf, JDAManager jdas) {
        super(conf, jdas);
    }

    @Override
    public void doActionOnCmd(Message msg, List<String> cmdArguments) {
        msg.reply("pong").submit();
    }

    @Override
    protected MessageEmbed fullHelpPage() {
        return new EmbedBuilder().setDescription("This command sends you a pong!").build();
    }

    
}
