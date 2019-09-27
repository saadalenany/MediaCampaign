package com.spring.mediacompaign.dao.repos;

import com.spring.mediacompaign.dao.entities.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, String> {
}
