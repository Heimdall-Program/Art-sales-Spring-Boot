package com.cursework.WebArtSell.Repo;

import com.cursework.WebArtSell.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}