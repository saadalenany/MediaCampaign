package com.spring.mediacompaign.services.impl;

import com.spring.mediacompaign.dao.entities.AdminEntity;
import com.spring.mediacompaign.dao.models.AdminModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.dao.repos.AdminRepository;
import com.spring.mediacompaign.mappers.AdminMapper;
import com.spring.mediacompaign.services.api.AdminService;
import com.spring.mediacompaign.services.validators.GeneralValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class AdminServiceImpl implements AdminService, GeneralValidator<AdminModel> {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public VersionedModel save(AdminModel adminModel) {
        final VersionedModel posted = post(adminModel);
        if (posted != null) {
            return posted;
        }
        AdminEntity adminEntity = adminRepository.save(adminMapper.toEntity(adminModel));
        return adminMapper.toModel(adminEntity);
    }

    @Override
    public VersionedModel update(AdminModel adminModel) {
        final VersionedModel putted = put(adminModel);
        if (putted != null) {
            return putted;
        }
        AdminEntity adminEntity = adminRepository.save(adminMapper.toEntity(adminModel));
        return adminMapper.toModel(adminEntity);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
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
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<AdminModel> list() {
        List<AdminEntity> all = adminRepository.findAll();
        return adminMapper.toModels(all);
    }

    @Override
    public AdminModel getByUsernameAndPassword(String name, String password) {
        final AdminEntity adminEntity = adminRepository.findByNameAndPassword(name, password).orElse(null);
        return adminMapper.toModel(adminEntity);
    }
}
