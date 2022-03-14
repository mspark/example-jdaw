package de.mspark.example.jdaw.guild;

import java.util.List;
import java.util.Optional;

import de.mspark.jdaw.Command;
import de.mspark.jdaw.CommandProperties;
import de.mspark.jdaw.DistributionSetting;
import de.mspark.jdaw.JDAManager;
import de.mspark.jdaw.config.JDAWConfig;
import de.mspark.jdaw.guilds.GuildConfigService;
import de.mspark.jdaw.guilds.GuildRepository;
import de.mspark.jdaw.guilds.model.CustomGuildConf;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

@CommandProperties(trigger = "prefix", description = "Sets the prefix", userGuildPermissions = Permission.MANAGE_SERVER, helpPage = false)
public class SetOwnPrefix extends Command {
    private GuildRepository repo;
    
    public SetOwnPrefix(JDAWConfig config, GuildConfigService guildConfig, GuildRepository repo, JDAManager jdas) {
        super(config, guildConfig, jdas, true);
        this.repo = repo;
    }

    @Override
    public void doActionOnCmd(Message msg, List<String> cmdArguments) {
        String prefix = filter(cmdArguments.get(0)).get();
        var guildConfig = repo.findById(msg.getGuild().getIdLong())
            .map(g -> new CustomGuildConf(g.id(), prefix, g.whitelist()))
            .orElse(new CustomGuildConf(msg.getGuild().getIdLong(), prefix));
        repo.save(guildConfig);
        msg.reply("ok").submit();
    }

    @Override
    protected MessageEmbed fullHelpPage() {
        // TODO Auto-generated method stub
        return null;
    }
    
    // !!! SQL Injection possible - just for example !!!
    public Optional<String> filter(String desiredPrefix) {
        return Optional.of(desiredPrefix);
    }
}
