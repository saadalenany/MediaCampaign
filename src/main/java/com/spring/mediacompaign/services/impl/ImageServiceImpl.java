package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.ImageEntity;
import com.spring.mediacompaign.dao.models.ImageModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.dao.repos.ImageRepository;
import com.spring.mediacompaign.mappers.ImageMapper;
import com.spring.mediacompaign.services.api.ImageService;
import com.spring.mediacompaign.services.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService, GeneralValidator<ImageModel> {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public VersionedModel save(ImageModel imageModel) {
        final VersionedModel posted = post(imageModel);
        if (posted != null) {
            return posted;
        }
        ImageEntity imageEntity = imageRepository.save(imageMapper.toEntity(imageModel));
        return imageMapper.toModel(imageEntity);
    }

    @Override
    public VersionedModel update(ImageModel imageModel) {
        final VersionedModel putted = put(imageModel);
        if (putted != null) {
            return putted;
        }
        ImageEntity imageEntity = imageRepository.save(imageMapper.toEntity(imageModel));
        return imageMapper.toModel(imageEntity);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
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
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<ImageModel> list() {
        List<ImageEntity> all = imageRepository.findAll();
        return imageMapper.toModels(all);
    }
}
