package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.TextModel;
import com.spring.mediacompaign.dao.models.VersionedModel;

import java.util.List;

public interface TextService {

    VersionedModel save(TextModel textModel);

    VersionedModel update(TextModel textModel);

    TextModel getById(String id);

    Boolean delete(String id);

    List<TextModel> list();
}
