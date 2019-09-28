package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.CampaignModel;

import java.util.List;

public interface CampaignService {

    CampaignModel save(CampaignModel campaignModel);

    CampaignModel update(CampaignModel campaignModel);

    CampaignModel getById(String id);

    Boolean delete(String id);

    List<CampaignModel> list();
}
