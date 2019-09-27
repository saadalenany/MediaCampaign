package com.spring.mediacompaign.dao.repos;

import com.spring.mediacompaign.dao.entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, String> {
}
