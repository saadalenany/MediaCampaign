package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.SocialPlatformModel;
import com.spring.mediacompaign.dao.models.VersionedModel;

import java.util.List;

public interface SocialPlatformService {

    VersionedModel save(SocialPlatformModel socialPlatformModel);

    VersionedModel update(SocialPlatformModel socialPlatformModel);

    SocialPlatformModel getById(String id);

    Boolean delete(String id);

    List<SocialPlatformModel> list();
}
