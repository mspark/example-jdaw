package de.mspark.example.jdaw;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.mspark.jdaw.cmdapi.TextCommand;
import de.mspark.jdaw.guilds.GuildRepository;
import de.mspark.jdaw.startup.JDAConfigModifier;
import de.mspark.jdaw.startup.JdawInstance;
import de.mspark.jdaw.startup.JdawInstanceBuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

@Configuration
public class BeanConfig {

    @Bean
    public JdawInstance instance(PropertiesConfig config, List<JDAConfigModifier> visitors, List<TextCommand> cmd,
            GuildRepository guildRepo) {
        var instance = new JdawInstanceBuilder(config).enableHelpCommand(config)
                .addJdaModifier(visitors.toArray(JDAConfigModifier[]::new))
                .addCommand(cmd.toArray(TextCommand[]::new))
                .enableGuildSpecificSettings(guildRepo)
                .buildJdawInstance();
        return instance;
    }

//    @Bean
//    public JDAManager manager(JdawInstance instance) {
//        return instance.jdaManager();
//    }
//
//    @Bean
//    public GuildConfigService gc(JdawInstance instance) {
//        return instance.guildConfig();
//    }

    @Bean
    // config for enabling member caching via intents
    public JDAConfigModifier memberCacheIntent() {
        return jda -> jda.setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS);
    }
}
