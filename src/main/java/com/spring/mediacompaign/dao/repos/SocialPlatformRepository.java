package com.spring.mediacompaign.dao.repos;

import com.spring.mediacompaign.dao.entities.SocialPlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialPlatformRepository extends JpaRepository<SocialPlatformEntity, String> {
}
