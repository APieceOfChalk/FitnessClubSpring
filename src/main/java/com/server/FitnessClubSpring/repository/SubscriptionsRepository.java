package com.server.FitnessClubSpring.repository;

import com.server.FitnessClubSpring.entity.Areas;
import com.server.FitnessClubSpring.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Long> {
}
