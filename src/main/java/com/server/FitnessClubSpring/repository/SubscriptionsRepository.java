package com.server.FitnessClubSpring.repository;

import com.server.FitnessClubSpring.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий абонементов.
 */
public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Long> {
}
