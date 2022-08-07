package de.mspark.example.jdaw.commands;

import static net.dv8tion.jda.api.Permission.MANAGE_ROLES;
import static net.dv8tion.jda.api.Permission.MANAGE_SERVER;

import java.util.List;

import de.mspark.jdaw.core.DistributionSetting;
import de.mspark.jdaw.core.TextCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.Role;

/**
 * You need to enable caching for this command in the JDA configuration. See
 * https://stackoverflow.com/questions/61226721/discord-jda-invalid-member-list
 */
public class DeleteCommand extends TextCommand {
    @Override
    public String trigger() {
        return "delete";
    }
    
    @Override
    public String description() {
        return "Deletes all member from one group.";
    }
    
    @Override
    public DistributionSetting distributionSetting() {
        return DistributionSetting.MAIN_ONLY;
    }
    
    @Override
    public Permission[] botGuildPermissions() {
        return new Permission[] { MANAGE_ROLES };
        
    }
    
    @Override
    public Permission[] userGuildPermissions() {
        return new Permission[] { MANAGE_SERVER };
    }
    
    @Override
        public MessageEmbed commandHelpPage() {
            return new EmbedBuilder().setDescription(description())
                    .addField(new Field("<GROUPID>", "Value defines the group of member to be deleted", false))
                    .build();
        }

    @Override
    public void doActionOnTrigger(Message msg, List<String> cmdArguments) {
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
}
