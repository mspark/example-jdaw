package de.mspark.example.jdaw.commands;

import static net.dv8tion.jda.api.Permission.*;

import java.util.List;

import de.mspark.jdaw.command.Command;
import de.mspark.jdaw.command.CommandProperties;
import de.mspark.jdaw.jda.JDAManager;
import de.mspark.jdaw.jda.JDAWConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

@CommandProperties(
        trigger = "test",
        description = "Demonstration of different JDA execution",
        botGuildPermissions = {KICK_MEMBERS, BAN_MEMBERS},
        userGuildPermissions = ADMINISTRATOR,
        executableWihtoutArgs = false
    )
public class BalanceTestCommand extends Command {
    private JDAManager jdas;

    /*
     * Due to the balancing option, this command is not executed on the main token only. 
     */
    public BalanceTestCommand(JDAWConfig conf, JDAManager jdas) {
        super(conf, jdas, true);
    }

    @Override
    public void doActionOnCmd(Message msg, List<String> cmdArguments) {
        // first index is safe because "executableWithoutArgs" is false!
        switch (cmdArguments.get(0)) {
        case "run" -> msg.getChannel().sendMessage("Works!").submit();
        // even without balancing the whole command, its possible to execute actions on other bot instances.
        case "runelsewhere" -> jdas.getNextJDA().getTextChannelById(msg.getChannel().getId()).sendMessage("ok").submit();
        default -> msg.reply("Unknown argument").submit();
        }
    }

    @Override
    public MessageEmbed fullHelpPage() {
        return new EmbedBuilder().setDescription("Hi!").build();
    }

}
