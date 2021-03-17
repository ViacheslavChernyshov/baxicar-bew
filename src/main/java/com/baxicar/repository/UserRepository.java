package com.baxicar.repository;

import com.baxicar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User save(User user);

    User findByUserId(Long userId);

    User findByEmailAndActiveTrue(String email);
}
