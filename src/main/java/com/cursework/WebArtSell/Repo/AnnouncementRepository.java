package com.cursework.WebArtSell.Repo;

import com.cursework.WebArtSell.Models.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {
}