package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.TargetPageModel;

import java.util.List;

public interface TargetPageService {

    TargetPageModel save(TargetPageModel targetPageModel);

    TargetPageModel update(TargetPageModel targetPageModel);

    TargetPageModel getById(String id);

    Boolean delete(String id);

    List<TargetPageModel> list();
}
