package com.csgo.repository;

import com.csgo.service.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
}
