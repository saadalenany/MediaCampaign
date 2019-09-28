package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.SourcePageModel;

import java.util.List;

public interface SourcePageService {

    SourcePageModel save(SourcePageModel sourcePageModel);

    SourcePageModel update(SourcePageModel sourcePageModel);

    SourcePageModel getById(String id);

    Boolean delete(String id);

    List<SourcePageModel> list();
}
