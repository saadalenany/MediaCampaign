package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.CampaignEntity;
import com.spring.mediacompaign.dao.models.CampaignModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.dao.repos.CampaignRepository;
import com.spring.mediacompaign.mappers.CampaignMapper;
import com.spring.mediacompaign.services.api.CampaignService;
import com.spring.mediacompaign.services.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService, GeneralValidator<CampaignModel> {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMapper campaignMapper;

    @Override
    public VersionedModel save(CampaignModel campaignModel) {
        final VersionedModel posted = post(campaignModel);
        if (posted != null) {
            return posted;
        }
        CampaignEntity campaignEntity = campaignRepository.save(campaignMapper.toEntity(campaignModel));
        return campaignMapper.toModel(campaignEntity);
    }

    @Override
    public VersionedModel update(CampaignModel campaignModel) {
        final VersionedModel putted = put(campaignModel);
        if (putted != null) {
            return putted;
        }
        CampaignEntity campaignEntity = campaignRepository.save(campaignMapper.toEntity(campaignModel));
        return campaignMapper.toModel(campaignEntity);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
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
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<CampaignModel> list() {
        List<CampaignEntity> all = campaignRepository.findAll();
        return campaignMapper.toModels(all);
    }
}
