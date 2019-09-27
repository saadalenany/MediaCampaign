package com.spring.mediacompaign.dao.repos;

import com.spring.mediacompaign.dao.entities.TextEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<TextEntity, String> {
}
