package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.VideoEntity;
import com.spring.mediacompaign.dao.models.VideoModel;
import com.spring.mediacompaign.dao.repos.VideoRepository;
import com.spring.mediacompaign.mappers.VideoMapper;
import com.spring.mediacompaign.services.api.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public VideoModel save(VideoModel videoModel) {
        VideoEntity saved = videoRepository.save(videoMapper.toEntity(videoModel));
        return videoMapper.toModel(saved);
    }

    @Override
    public VideoModel update(VideoModel videoModel) {
        VideoEntity saved = videoRepository.save(videoMapper.toEntity(videoModel));
        return videoMapper.toModel(saved);
    }

    @Override
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
    public List<VideoModel> list() {
        List<VideoEntity> all = videoRepository.findAll();
        return videoMapper.toModels(all);
    }
}
