package com.spring.mediacompaign.dao.repos;

import com.spring.mediacompaign.dao.entities.TargetPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetPageRepository extends JpaRepository<TargetPageEntity, String> {
}
