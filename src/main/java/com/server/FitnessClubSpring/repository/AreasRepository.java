package com.server.FitnessClubSpring.repository;

import com.server.FitnessClubSpring.entity.Areas;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий залов.
 */
public interface AreasRepository extends JpaRepository<Areas, Long> {
}
