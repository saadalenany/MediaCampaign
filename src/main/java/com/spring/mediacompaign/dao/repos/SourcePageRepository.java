package com.spring.mediacompaign.dao.repos;

import com.spring.mediacompaign.dao.entities.SourcePageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourcePageRepository extends JpaRepository<SourcePageEntity, String> {

    Optional<SourcePageEntity> findByPageUrl(String pageUrl);

}
