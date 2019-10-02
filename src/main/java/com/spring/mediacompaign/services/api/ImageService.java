package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.ImageModel;
import com.spring.mediacompaign.dao.models.VersionedModel;

import java.util.List;

public interface ImageService {

    VersionedModel save(ImageModel imageModel);

    VersionedModel update(ImageModel imageModel);

    ImageModel getById(String id);

    Boolean delete(String id);

    List<ImageModel> list();
}
