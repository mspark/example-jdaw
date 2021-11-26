package de.mspark.example.jdaw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.mspark.jdaw.jda.JDAConfigurationVisitor;
import de.mspark.jdaw.jda.JDAWConfig;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

@Configuration
public class CustomJdaConfig {
    @Bean
    public JDAConfigurationVisitor jdaConfigurationVisitor() {
        return jda -> jda.setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS);
    }
    
    @Bean
    public JDAWConfig jdawConfig() {
        return new JDAWConfig() {
            
            @Override
            public String prefix() {
                return "!";
            }
            
            @Override
            public String[] apiTokens() {
                return new String[] {""};
            }
        };
    }
}

