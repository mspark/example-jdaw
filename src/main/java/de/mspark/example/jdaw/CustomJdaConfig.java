package de.mspark.example.jdaw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.mspark.jdaw.config.JDAConfigurationVisitor;
import de.mspark.jdaw.config.JDAWConfig;
import de.mspark.jdaw.help.HelpConfig;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

@Configuration
public class CustomJdaConfig {
    
    @Bean
    // config for enabling member caching via intent
    public JDAConfigurationVisitor jdaConfigurationVisitor() {
        return jda -> jda.setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS);
    }
    
    @Bean
    // mandatory config
    public JDAWConfig jdawConfig() {
        return new JDAWConfig() {
            
            @Override
            public String defaultPrefix() {
                return "!";
            }
            
            @Override
            public String[] apiTokens() {
                return new String[] {""};
            }
        };
    }
    
    @Bean
    // This enabled a global help command
    public HelpConfig helpConfig() {
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
