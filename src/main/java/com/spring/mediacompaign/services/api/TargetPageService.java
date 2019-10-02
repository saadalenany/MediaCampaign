package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.TargetPageModel;
import com.spring.mediacompaign.dao.models.VersionedModel;

import java.util.List;

public interface TargetPageService {

    VersionedModel save(TargetPageModel targetPageModel);

    VersionedModel update(TargetPageModel targetPageModel);

    TargetPageModel getById(String id);

    Boolean delete(String id);

    List<TargetPageModel> list();
}
