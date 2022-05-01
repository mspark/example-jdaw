package de.mspark.example.jdaw.guild;

import static net.dv8tion.jda.api.Permission.MANAGE_SERVER;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.mspark.jdaw.core.TextCommand;
import de.mspark.jdaw.guilds.CustomGuildConf;
import de.mspark.jdaw.guilds.GuildRepository;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

@Component
public class SetOwnPrefix extends TextCommand {
    
    @Autowired
    private GuildRepository repo;

    @Override
    public String trigger() {
        return "prefix";
    }

    @Override
    public String description() {
        return "Sets a prefix";
    }

    @Override
    public Permission[] userGuildPermissions() {
        return new Permission[] { MANAGE_SERVER };
    }

    @Override
    public void doActionOnTrigger(Message msg, List<String> cmdArguments) {
        String prefix = filter(cmdArguments.get(0)).get();
        var guildConfig = repo.findById(msg.getGuild().getIdLong())
            .map(g -> new CustomGuildConf(g.id(), prefix, g.whitelist()))
            .orElse(new CustomGuildConf(msg.getGuild().getIdLong(), prefix));
        repo.save(guildConfig);
        msg.reply("ok").submit();
    }

    @Override
    public MessageEmbed commandHelpPage() {
        return new EmbedBuilder().setDescription(
            "Changes the prefix for the current guild to another one. First argument is the new prefix").build();
    }

    // just as an example. Use better filter for SQL injections etc.
    public Optional<String> filter(String desiredPrefix) {
        return Optional.of(desiredPrefix).filter(s -> !s.contains(" "));
    }

}
