package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.SourcePageModel;
import com.spring.mediacompaign.dao.models.VersionedModel;

import java.util.List;

public interface SourcePageService {

    VersionedModel save(SourcePageModel sourcePageModel);

    VersionedModel update(SourcePageModel sourcePageModel);

    SourcePageModel getById(String id);

    SourcePageModel getByPageUrl(String pageUrl);

    Boolean delete(String id);

    List<SourcePageModel> list();
}
