package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.AdminModel;
import com.spring.mediacompaign.dao.models.VersionedModel;

import java.util.List;

public interface AdminService {

    VersionedModel save(AdminModel adminModel);

    VersionedModel update(AdminModel adminModel);

    AdminModel getById(String id);

    Boolean delete(String id);

    List<AdminModel> list();
}
