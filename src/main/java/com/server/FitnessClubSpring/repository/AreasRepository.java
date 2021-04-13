package com.server.FitnessClubSpring.repository;

import com.server.FitnessClubSpring.entity.Areas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreasRepository extends JpaRepository<Areas, Long> {
    Areas findByName(String name);
}
