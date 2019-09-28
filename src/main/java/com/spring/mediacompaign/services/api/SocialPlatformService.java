package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.SocialPlatformModel;

import java.util.List;

public interface SocialPlatformService {

    SocialPlatformModel save(SocialPlatformModel socialPlatformModel);

    SocialPlatformModel update(SocialPlatformModel socialPlatformModel);

    SocialPlatformModel getById(String id);

    Boolean delete(String id);

    List<SocialPlatformModel> list();
}
