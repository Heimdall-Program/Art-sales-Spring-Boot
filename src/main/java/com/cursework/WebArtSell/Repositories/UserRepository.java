package com.cursework.WebArtSell.Repositories;

import com.cursework.WebArtSell.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
}