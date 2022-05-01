package de.mspark.example.jdaw;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.mspark.jdaw.config.JDAConfigurationVisitor;
import de.mspark.jdaw.config.JDAManager;
import de.mspark.jdaw.config.JdawBuilder;
import de.mspark.jdaw.config.JdawInstance;
import de.mspark.jdaw.core.TextCommand;
import de.mspark.jdaw.guilds.GuildConfigService;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

@Configuration
public class BeanConfig {
    
    @Bean
    public JdawInstance instance(PropertiesConfig config, List<JDAConfigurationVisitor> visitors, List<TextCommand> cmd) {
        var instance = new JdawBuilder(config).enableHelpCommand(config)
            .setJdawConfigurationVisitors(visitors)
            .buildInstance();
        cmd.forEach(instance::register);
        instance.start();
        return instance;
    }
    
    @Bean
    public JDAManager manager(JdawInstance instance) {
        return instance.jdaManager();
    }

    @Bean
    public GuildConfigService gc(JdawInstance instance) {
        return instance.guildConfig();
    }
    
    @Bean
    // config for enabling member caching via intents
    public JDAConfigurationVisitor jdaConfigurationVisitor() {
        return jda -> jda.setChunkingFilter(ChunkingFilter.ALL)
            .setMemberCachePolicy(MemberCachePolicy.ALL)
            .enableIntents(GatewayIntent.GUILD_MEMBERS);
    }
}
