package de.mspark.example.jdaw.guild;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.mspark.jdaw.guilds.CustomGuildConf;

@Repository
public interface JpaGuildRepo extends JpaRepository<CustomGuildConf, Long> {}