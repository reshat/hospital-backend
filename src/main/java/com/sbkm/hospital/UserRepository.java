package com.sbkm.hospital;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginOrEmail(String username, String email);
    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);
}
