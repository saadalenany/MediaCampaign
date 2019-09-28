package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.ImageEntity;
import com.spring.mediacompaign.dao.models.ImageModel;
import com.spring.mediacompaign.dao.repos.ImageRepository;
import com.spring.mediacompaign.mappers.ImageMapper;
import com.spring.mediacompaign.services.api.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public ImageModel save(ImageModel imageModel) {
        ImageEntity imageEntity = imageRepository.save(imageMapper.toEntity(imageModel));
        return imageMapper.toModel(imageEntity);
    }

    @Override
    public ImageModel update(ImageModel imageModel) {
        ImageEntity imageEntity = imageRepository.save(imageMapper.toEntity(imageModel));
        return imageMapper.toModel(imageEntity);
    }

    @Override
    public ImageModel getById(String id) {
        ImageEntity imageEntity = imageRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No Image found with this id [%s]", id)));
        return imageMapper.toModel(imageEntity);
    }

    @Override
    public Boolean delete(String id) {
        imageRepository.deleteById(id);
        return !imageRepository.findById(id).isPresent();
    }

    @Override
    public List<ImageModel> list() {
        List<ImageEntity> all = imageRepository.findAll();
        return imageMapper.toModels(all);
    }
}
