package com.spring.mediacompaign.services;

import com.spring.mediacompaign.dao.entities.AdminEntity;
import com.spring.mediacompaign.dao.models.AdminModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.services.api.AdminService;
import com.spring.mediacompaign.utils.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceImplTest extends BaseTest {

    @Autowired
    private AdminService adminService;

    @Test
    void testSave_success() {
        AdminModel adminModel = createAdmin();

        final AdminModel saved = (AdminModel) adminService.save(adminModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(adminModel.getName(), saved.getName());
        assertEquals(adminModel.getPassword(), saved.getPassword());
    }

    @Test
    void testSave_failure() {
        AdminModel adminModel = createAdmin();

        adminModel.setId(RandomStringUtils.randomAlphanumeric(10));
        final VersionedModel error = adminService.save(adminModel);
        assertEquals("for post ID must be null", error.getErrorMessage());
    }

    @Test
    void testUpdate_success() {
        AdminModel adminModel = createAdmin();

        AdminModel saved = (AdminModel) adminService.save(adminModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(adminModel.getName(), saved.getName());
        assertEquals(adminModel.getPassword(), saved.getPassword());

        final String updatedPassword = saved.getPassword() + "-updated";
        saved.setPassword(updatedPassword);

        final AdminModel updated = (AdminModel) adminService.update(saved);
        assertEquals(updatedPassword, updated.getPassword());
    }

    @Test
    void testUpdate_failure() {
        AdminModel adminModel = createAdmin();

        final VersionedModel updated = adminService.update(adminModel);
        assertEquals("for put ID must not be null", updated.getErrorMessage());
    }

    @Test
    void testGetById_Success() {
        AdminModel adminModel = createAdmin();

        AdminModel saved = (AdminModel) adminService.save(adminModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(adminModel.getName(), saved.getName());
        assertEquals(adminModel.getPassword(), saved.getPassword());

        final AdminModel byId = adminService.getById(saved.getId());
        assertEquals(saved.getId(), byId.getId());
        assertEquals(saved.getName(), byId.getName());
        assertEquals(saved.getPassword(), byId.getPassword());
    }

    @Test
    void testGetById_failure() {
        try {
            adminService.getById("X");
        } catch (RuntimeException ex) {
            assertEquals("No Admin found with this id [X]", ex.getMessage());
        }
    }

    @Test
    void testDelete_success() {
        AdminModel adminModel = createAdmin();

        AdminModel saved = (AdminModel) adminService.save(adminModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(adminModel.getName(), saved.getName());
        assertEquals(adminModel.getPassword(), saved.getPassword());

        final Boolean deleted = adminService.delete(saved.getId());
        assertTrue(deleted);
    }

    @Test
    void testDelete_failure() {
        try {
            adminService.delete("X");
        } catch (EmptyResultDataAccessException ex) {
            assertEquals("No class " + AdminEntity.class.getName() + " entity with id X exists!", ex.getMessage());
        }
    }

    @Test
    void testList_success() {
        final List<AdminModel> list = adminService.list();
        AdminModel adminModel = createAdmin();

        final AdminModel saved = (AdminModel) adminService.save(adminModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(adminModel.getName(), saved.getName());
        assertEquals(adminModel.getPassword(), saved.getPassword());

        final List<AdminModel> newList = adminService.list();
        assertEquals(list.size() + 1, newList.size());
    }

    private AdminModel createAdmin() {
        AdminModel adminModel = new AdminModel();
        adminModel.setName(RandomStringUtils.randomAlphanumeric(10));
        adminModel.setPassword(RandomStringUtils.randomAlphanumeric(10));
        return adminModel;
    }
}
