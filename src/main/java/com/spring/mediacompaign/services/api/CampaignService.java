package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.CampaignModel;
import com.spring.mediacompaign.dao.models.VersionedModel;

import java.util.List;

public interface CampaignService {

    VersionedModel save(CampaignModel campaignModel);

    VersionedModel update(CampaignModel campaignModel);

    CampaignModel getById(String id);

    Boolean delete(String id);

    List<CampaignModel> list();
}
