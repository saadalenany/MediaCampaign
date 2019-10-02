package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.SocialPlatformEntity;
import com.spring.mediacompaign.dao.models.SocialPlatformModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.dao.repos.SocialPlatformRepository;
import com.spring.mediacompaign.mappers.SocialPlatformMapper;
import com.spring.mediacompaign.services.api.SocialPlatformService;
import com.spring.mediacompaign.services.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SocialPlatformServiceImpl implements SocialPlatformService, GeneralValidator<SocialPlatformModel> {

    @Autowired
    private SocialPlatformRepository socialPlatformRepository;

    @Autowired
    private SocialPlatformMapper socialPlatformMapper;

    @Override
    public VersionedModel save(SocialPlatformModel socialPlatformModel) {
        final VersionedModel posted = post(socialPlatformModel);
        if (posted != null) {
            return posted;
        }
        SocialPlatformEntity saved = socialPlatformRepository.save(socialPlatformMapper.toEntity(socialPlatformModel));
        return socialPlatformMapper.toModel(saved);
    }

    @Override
    public VersionedModel update(SocialPlatformModel socialPlatformModel) {
        final VersionedModel putted = put(socialPlatformModel);
        if (putted != null) {
            return putted;
        }
        SocialPlatformEntity saved = socialPlatformRepository.save(socialPlatformMapper.toEntity(socialPlatformModel));
        return socialPlatformMapper.toModel(saved);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public SocialPlatformModel getById(String id) {
        SocialPlatformEntity socialPlatformEntity = socialPlatformRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No SocialPlatform found with this id [%s]", id)));
        return socialPlatformMapper.toModel(socialPlatformEntity);
    }

    @Override
    public Boolean delete(String id) {
        socialPlatformRepository.deleteById(id);
        return !socialPlatformRepository.findById(id).isPresent();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<SocialPlatformModel> list() {
        List<SocialPlatformEntity> all = socialPlatformRepository.findAll();
        return socialPlatformMapper.toModels(all);
    }
}
