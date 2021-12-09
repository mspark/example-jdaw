package de.mspark.example.jdaw.commands;

import static net.dv8tion.jda.api.Permission.MANAGE_ROLES;
import static net.dv8tion.jda.api.Permission.MANAGE_SERVER;

import java.util.List;

import de.mspark.jdaw.Command;
import de.mspark.jdaw.CommandProperties;
import de.mspark.jdaw.JDAManager;
import de.mspark.jdaw.config.JDAWConfig;
import de.mspark.jdaw.guilds.GuildConfigService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;

/**
 * 
 * You need to enable caching for this command in the JDA configuration. See 
 * https://stackoverflow.com/questions/61226721/discord-jda-invalid-member-list
 */
@CommandProperties(trigger = "delete", description = "Deletes all member from one group.",
    botGuildPermissions = MANAGE_ROLES, userGuildPermissions = MANAGE_SERVER,
    executableWihtoutArgs = false, helpPage = false)
public class DeleteCommand extends Command {

    public DeleteCommand(JDAWConfig conf, GuildConfigService gc, JDAManager jdas) {
        super(conf, gc, jdas, false);
    }

    @Override
    public void doActionOnCmd(Message msg, List<String> cmdArguments) {
        try {
            long id = Long.parseLong(cmdArguments.get(0));
            Role role = msg.getGuild().getRoleById(id);
            msg.getGuild()
                .getMembers().stream()
                .filter(m -> m.getRoles().stream().anyMatch(r -> r.getId().equals(role.getId())))
                .forEach(m -> msg.getGuild().removeRoleFromMember(m.getId(), role).queue());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid property. Pls use a discord id");
        }

    }

    @Override
    protected MessageEmbed fullHelpPage() {
        // even with content it wouldn't visible in the global help command because the helpPage is disabled 
        // in the command properties.
        return null;
    }
}
