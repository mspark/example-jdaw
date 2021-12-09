package de.mspark.example.jdaw.guild;

import java.util.Optional;

import org.springframework.stereotype.Component;

import de.mspark.jdaw.guilds.GuildRepository;
import de.mspark.jdaw.guilds.model.CustomGuildConf;

@Component
public class GuildRepoImpl implements GuildRepository {

    @Override
    public CustomGuildConf save(CustomGuildConf g) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(CustomGuildConf entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean exists(Long gid) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<CustomGuildConf> findOne(Long gid) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
//public interface GuildRepoImpl extends CrudRepository<Employee, Long> {}
