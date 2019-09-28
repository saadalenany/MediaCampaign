package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.TextModel;

import java.util.List;

public interface TextService {

    TextModel save(TextModel textModel);

    TextModel update(TextModel textModel);

    TextModel getById(String id);

    Boolean delete(String id);

    List<TextModel> list();
}
