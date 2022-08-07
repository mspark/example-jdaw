package de.mspark.example.jdaw;

import de.mspark.example.jdaw.commands.BalanceTestCommand;
import de.mspark.example.jdaw.commands.DeleteCommand;
import de.mspark.jdaw.config.JDAConfigurationVisitor;
import de.mspark.jdaw.config.JDAWConfig;
import de.mspark.jdaw.config.JdawInstanceBuilder;
import de.mspark.jdaw.help.HelpConfig;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class App {

    public static void main(String[] args) {
        var instance = new JdawInstanceBuilder(jdawConfig())
            .enableHelpCommand(helpConfig())
            .setJdawConfigurationVisitors(jdaConfigurationVisitor())
            .addForRegister(new BalanceTestCommand())
            .addForRegister(new DeleteCommand())
            .buildJdawInstance();
        instance.getRegisterdActions().forEach(a -> System.out.println("Listening on trigger " + a.trigger()));
        // use instance.register(cmd) for adding additional commands later
    }

    // config for enabling member caching via intent
    public static JDAConfigurationVisitor jdaConfigurationVisitor() {
        return jda -> jda.setChunkingFilter(ChunkingFilter.ALL)
            .setMemberCachePolicy(MemberCachePolicy.ALL)
            .enableIntents(GatewayIntent.GUILD_MEMBERS);
    }

    // mandatory config
    public static JDAWConfig jdawConfig() {
        return new JDAWConfig() {

            @Override
            public String defaultPrefix() {
                return "!";
            }

            @Override
            public String[] apiTokens() {
                return new String[] { "" };
            }
        };
    }

    // This enabled a global help command
    public static HelpConfig helpConfig() {
        return new HelpConfig() {

            @Override
            public String botName() {
                return "testbot";
            }

            @Override
            public String botDescription() {
                return "Bot for testing - this is shown at the global help page";
            }
        };
    }
}
