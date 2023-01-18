package de.mspark.example.jdaw.guild;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.mspark.jdaw.guilds.GuildRepository;
import de.mspark.jdaw.guilds.SingleGuildSettings;


@Repository
interface GuildCrudRepo extends CrudRepository<SingleGuildSettings, Long>, GuildRepository {
}
