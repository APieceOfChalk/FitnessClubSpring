package com.server.FitnessClubSpring.repository;

import com.server.FitnessClubSpring.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
    Clients findByName(String name);
}
