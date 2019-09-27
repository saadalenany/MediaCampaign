package com.spring.mediacompaign.dao.repos;

import com.spring.mediacompaign.dao.entities.SourcePageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourcePageReository extends JpaRepository<SourcePageEntity, String> {
}
