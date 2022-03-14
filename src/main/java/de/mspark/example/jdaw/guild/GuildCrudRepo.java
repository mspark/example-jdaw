package de.mspark.example.jdaw.guild;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.mspark.jdaw.guilds.GuildRepository;
import de.mspark.jdaw.guilds.model.CustomGuildConf;

@Repository
@Primary
public interface GuildCrudRepo extends CrudRepository<CustomGuildConf, Long>, GuildRepository {}