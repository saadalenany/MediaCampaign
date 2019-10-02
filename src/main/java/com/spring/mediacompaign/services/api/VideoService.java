package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.dao.models.VideoModel;

import java.util.List;

public interface VideoService {

    VersionedModel save(VideoModel videoModel);

    VersionedModel update(VideoModel videoModel);

    VideoModel getById(String id);

    Boolean delete(String id);

    List<VideoModel> list();
}
