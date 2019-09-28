package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.AdminEntity;
import com.spring.mediacompaign.dao.models.AdminModel;
import com.spring.mediacompaign.dao.repos.AdminRepository;
import com.spring.mediacompaign.mappers.AdminMapper;
import com.spring.mediacompaign.services.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminModel save(AdminModel adminModel) {
        AdminEntity adminEntity = adminRepository.save(adminMapper.toEntity(adminModel));
        return adminMapper.toModel(adminEntity);
    }

    @Override
    public AdminModel update(AdminModel adminModel) {
        AdminEntity adminEntity = adminRepository.save(adminMapper.toEntity(adminModel));
        return adminMapper.toModel(adminEntity);
    }

    @Override
    public AdminModel getById(String id) {
        AdminEntity adminEntity = adminRepository.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("No Admin found with this id [%s]", id)));
        return adminMapper.toModel(adminEntity);
    }

    @Override
    public Boolean delete(String id) {
        adminRepository.deleteById(id);
        return !adminRepository.findById(id).isPresent();
    }

    @Override
    public List<AdminModel> list() {
        List<AdminEntity> all = adminRepository.findAll();
        return adminMapper.toModels(all);
    }
}
