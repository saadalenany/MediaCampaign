package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.PostModel;

import java.util.List;

public interface PostService {

    PostModel save(PostModel postModel);

    PostModel update(PostModel postModel);

    PostModel getById(String id);

    Boolean delete(String id);

    List<PostModel> list();
}
