package com.cursework.WebArtSell.Repo;

import com.cursework.WebArtSell.Models.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Announcement, Long> {
}
