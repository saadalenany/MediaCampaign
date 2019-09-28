package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.CampaignEntity;
import com.spring.mediacompaign.dao.models.CampaignModel;
import com.spring.mediacompaign.dao.repos.CampaignRepository;
import com.spring.mediacompaign.mappers.CampaignMapper;
import com.spring.mediacompaign.services.api.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMapper campaignMapper;

    @Override
    public CampaignModel save(CampaignModel campaignModel) {
        CampaignEntity campaignEntity = campaignRepository.save(campaignMapper.toEntity(campaignModel));
        return campaignMapper.toModel(campaignEntity);
    }

    @Override
    public CampaignModel update(CampaignModel campaignModel) {
        CampaignEntity campaignEntity = campaignRepository.save(campaignMapper.toEntity(campaignModel));
        return campaignMapper.toModel(campaignEntity);
    }

    @Override
    public CampaignModel getById(String id) {
        CampaignEntity campaignEntity = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("No Campaign found with this id [%s]", id)));
        return campaignMapper.toModel(campaignEntity);
    }

    @Override
    public Boolean delete(String id) {
        campaignRepository.deleteById(id);
        return !campaignRepository.findById(id).isPresent();
    }

    @Override
    public List<CampaignModel> list() {
        List<CampaignEntity> all = campaignRepository.findAll();
        return campaignMapper.toModels(all);
    }
}
