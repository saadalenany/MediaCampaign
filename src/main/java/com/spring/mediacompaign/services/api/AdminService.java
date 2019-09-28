package com.spring.mediacompaign.services.api;

import com.spring.mediacompaign.dao.models.AdminModel;

import java.util.List;

public interface AdminService {

    AdminModel save(AdminModel adminModel);

    AdminModel update(AdminModel adminModel);

    AdminModel getById(String id);

    Boolean delete(String id);

    List<AdminModel> list();
}
