package de.mspark.example.jdaw.commands;

import static net.dv8tion.jda.api.Permission.MANAGE_ROLES;
import static net.dv8tion.jda.api.Permission.MANAGE_SERVER;

import java.util.List;

import org.springframework.stereotype.Component;

import de.mspark.jdaw.cmdapi.DistributionSetting;
import de.mspark.jdaw.cmdapi.TextCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;

/**
 * 
 * You need to enable caching for this command in the JDA configuration. See
 * https://stackoverflow.com/questions/61226721/discord-jda-invalid-member-list
 */
@Component
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
    public void onTrigger(Message msg, List<String> cmdArguments) {
        try {
            long id = Long.parseLong(cmdArguments.get(0));
            Role role = msg.getGuild().getRoleById(id);
            msg.getGuild()
                .getMembers().stream()
                .filter(m -> m.getRoles().stream().anyMatch(r -> r.getId().equals(role.getId())))
                .forEach(m -> msg.getGuild().removeRoleFromMember(m, role).queue());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid property. Pls use a discord id");
        }

    }
}
