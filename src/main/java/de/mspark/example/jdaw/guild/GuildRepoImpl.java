package de.mspark.example.jdaw.guild;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import de.mspark.jdaw.guilds.GuildRepository;
import de.mspark.jdaw.guilds.model.CustomGuildConf;

@Repository
@Primary
public class GuildRepoImpl implements GuildRepository {

    private final ConcurrentLinkedQueue<CustomGuildConf> confs = new ConcurrentLinkedQueue<>();
    
    @Override
    public CustomGuildConf save(CustomGuildConf g) {
        confs.add(g);
        return g;
    }

    @Override
    public void delete(CustomGuildConf entity) {
        confs.remove(entity);
        
    }

    @Override
    public boolean exists(Long gid) {
        return confs.stream().anyMatch(g -> g.id() == gid.longValue());
    }

    @Override
    public Optional<CustomGuildConf> findOne(Long gid) {
        return confs.stream().filter(g -> g.id() == gid.longValue()).findAny();
    }
    
}
//public interface GuildRepoImpl extends CrudRepository<Employee, Long> {}
