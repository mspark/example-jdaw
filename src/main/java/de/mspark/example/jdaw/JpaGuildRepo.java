package de.mspark.example.jdaw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.mspark.jdaw.guilds.model.CustomGuildConf;

@Repository
public interface JpaGuildRepo extends JpaRepository<CustomGuildConf, Long> {}