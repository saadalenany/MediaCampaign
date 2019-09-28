package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.VideoModel;

import java.util.List;

public interface VideoService {

    VideoModel save(VideoModel videoModel);

    VideoModel update(VideoModel videoModel);

    VideoModel getById(String id);

    Boolean delete(String id);

    List<VideoModel> list();
}
