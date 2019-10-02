package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.PostModel;
import com.spring.mediacompaign.dao.models.VersionedModel;

import java.util.List;

public interface PostService {

    VersionedModel save(PostModel postModel);

    VersionedModel update(PostModel postModel);

    PostModel getById(String id);

    Boolean delete(String id);

    List<PostModel> list();
}
