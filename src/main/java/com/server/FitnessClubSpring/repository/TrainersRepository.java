package com.server.FitnessClubSpring.repository;

import com.server.FitnessClubSpring.entity.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий тренеров с возможностью поиска по части ФИО (в клиенте не используется).
 */
public interface TrainersRepository extends JpaRepository<Trainers, Long> {
    List<Trainers> findByNameContaining(String name);
}
