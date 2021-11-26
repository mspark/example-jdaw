package de.mspark.example.jdaw.commands;

import java.util.List;

import de.mspark.jdaw.command.Command;
import de.mspark.jdaw.command.EnableHelpCommand;
import de.mspark.jdaw.command.GlobalHelpCommand;
import de.mspark.jdaw.jda.JDAManager;
import de.mspark.jdaw.jda.JDAWConfig;

@EnableHelpCommand
public class HelpCommand extends GlobalHelpCommand {

    public HelpCommand(JDAWConfig conf, JDAManager jdas, List<Command> allLoadedCmds) {
        super(conf, jdas, allLoadedCmds);
    }

    @Override
    public String botDescription() {
        return """
                This is a example project for JDAW with a few commands.
                """;
    }

}
