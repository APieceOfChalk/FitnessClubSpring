package com.server.FitnessClubSpring.repository;

import com.server.FitnessClubSpring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
