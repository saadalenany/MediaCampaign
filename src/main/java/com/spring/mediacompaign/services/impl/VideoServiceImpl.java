package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.VideoEntity;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.dao.models.VideoModel;
import com.spring.mediacompaign.dao.repos.VideoRepository;
import com.spring.mediacompaign.mappers.VideoMapper;
import com.spring.mediacompaign.services.api.VideoService;
import com.spring.mediacompaign.services.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService, GeneralValidator<VideoModel> {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public VersionedModel save(VideoModel videoModel) {
        final VersionedModel posted = post(videoModel);
        if (posted != null) {
            return posted;
        }
        VideoEntity saved = videoRepository.save(videoMapper.toEntity(videoModel));
        return videoMapper.toModel(saved);
    }

    @Override
    public VersionedModel update(VideoModel videoModel) {
        final VersionedModel putted = put(videoModel);
        if (putted != null) {
            return putted;
        }
        VideoEntity saved = videoRepository.save(videoMapper.toEntity(videoModel));
        return videoMapper.toModel(saved);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public VideoModel getById(String id) {
        VideoEntity videoEntity = videoRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No Video found with this id [%s]", id)));
        return videoMapper.toModel(videoEntity);
    }

    @Override
    public Boolean delete(String id) {
        videoRepository.deleteById(id);
        return !videoRepository.findById(id).isPresent();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<VideoModel> list() {
        List<VideoEntity> all = videoRepository.findAll();
        return videoMapper.toModels(all);
    }
}
