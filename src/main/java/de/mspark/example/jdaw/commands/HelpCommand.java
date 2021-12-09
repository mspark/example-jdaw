package de.mspark.example.jdaw.commands;

import java.util.List;

import de.mspark.jdaw.Command;
import de.mspark.jdaw.JDAManager;
import de.mspark.jdaw.config.JDAWConfig;
import de.mspark.jdaw.help.EnableHelpCommand;
import de.mspark.jdaw.help.GlobalHelpCommand;

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

    @Override
    public String botName() {
        return "testbot";
    }

}
