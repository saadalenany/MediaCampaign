package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.SocialPlatformEntity;
import com.spring.mediacompaign.dao.models.SocialPlatformModel;
import com.spring.mediacompaign.dao.repos.SocialPlatformRepository;
import com.spring.mediacompaign.mappers.SocialPlatformMapper;
import com.spring.mediacompaign.services.api.SocialPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialPlatformServiceImpl implements SocialPlatformService {

    @Autowired
    private SocialPlatformRepository socialPlatformRepository;

    @Autowired
    private SocialPlatformMapper socialPlatformMapper;

    @Override
    public SocialPlatformModel save(SocialPlatformModel socialPlatformModel) {
        SocialPlatformEntity saved = socialPlatformRepository.save(socialPlatformMapper.toEntity(socialPlatformModel));
        return socialPlatformMapper.toModel(saved);
    }

    @Override
    public SocialPlatformModel update(SocialPlatformModel socialPlatformModel) {
        SocialPlatformEntity saved = socialPlatformRepository.save(socialPlatformMapper.toEntity(socialPlatformModel));
        return socialPlatformMapper.toModel(saved);
    }

    @Override
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
    public List<SocialPlatformModel> list() {
        List<SocialPlatformEntity> all = socialPlatformRepository.findAll();
        return socialPlatformMapper.toModels(all);
    }
}
