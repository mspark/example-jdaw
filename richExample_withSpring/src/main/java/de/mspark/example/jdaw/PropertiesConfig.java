package de.mspark.example.jdaw;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import de.mspark.jdaw.help.HelpConfig;
import de.mspark.jdaw.startup.JdawConfig;

@ConstructorBinding
@ConfigurationProperties(prefix = "bot")
public record PropertiesConfig(String defaultPrefix, String[] apiTokens, String botName, String botDescription)
    implements JdawConfig, HelpConfig {
}
