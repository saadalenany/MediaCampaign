package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.ImageModel;

import java.util.List;

public interface ImageService {

    ImageModel save(ImageModel imageModel);

    ImageModel update(ImageModel imageModel);

    ImageModel getById(String id);

    Boolean delete(String id);

    List<ImageModel> list();
}
