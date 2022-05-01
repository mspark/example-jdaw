package de.mspark.example.jdaw;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import de.mspark.jdaw.config.JDAWConfig;
import de.mspark.jdaw.help.HelpConfig;

@ConstructorBinding
@ConfigurationProperties(prefix = "bot")
public record PropertiesConfig(String defaultPrefix, String[] apiTokens, String botName, String botDescription)
    implements JDAWConfig, HelpConfig {
}
