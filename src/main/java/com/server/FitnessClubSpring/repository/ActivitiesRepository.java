package com.server.FitnessClubSpring.repository;

import com.server.FitnessClubSpring.entity.Activities;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий занятий.
 */
public interface ActivitiesRepository extends JpaRepository<Activities, Long> {
}
