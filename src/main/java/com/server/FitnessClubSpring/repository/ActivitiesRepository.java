package com.server.FitnessClubSpring.repository;

import com.server.FitnessClubSpring.entity.Activities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitiesRepository extends JpaRepository<Activities, Long> {
    Activities findByName(String name);
}
