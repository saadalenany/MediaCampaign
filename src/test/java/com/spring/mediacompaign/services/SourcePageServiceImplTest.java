package com.spring.mediacompaign.services;

import com.spring.mediacompaign.dao.entities.AdminEntity;
import com.spring.mediacompaign.dao.entities.SourcePageEntity;
import com.spring.mediacompaign.dao.models.AdminModel;
import com.spring.mediacompaign.dao.models.SourcePageModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.services.api.AdminService;
import com.spring.mediacompaign.services.api.SourcePageService;
import com.spring.mediacompaign.utils.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SourcePageServiceImplTest extends BaseTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SourcePageService sourcePageService;

    @Test
    void testSave_success() {
        final SourcePageModel sourcePage = createSourcePage();

        final SourcePageModel saved = (SourcePageModel) sourcePageService.save(sourcePage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(sourcePage.getOwnerId(), saved.getOwnerId());
        assertEquals(sourcePage.getPageUrl(), saved.getPageUrl());
        assertEquals(sourcePage.getUsername(), saved.getUsername());
        assertEquals(sourcePage.getPassword(), saved.getPassword());
    }

    @Test
    void testSave_failure() {
        final SourcePageModel sourcePage = createSourcePage();

        final SourcePageModel saved = (SourcePageModel) sourcePageService.save(sourcePage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(sourcePage.getOwnerId(), saved.getOwnerId());
        assertEquals(sourcePage.getPageUrl(), saved.getPageUrl());
        assertEquals(sourcePage.getUsername(), saved.getUsername());
        assertEquals(sourcePage.getPassword(), saved.getPassword());

        final VersionedModel error = sourcePageService.save(saved);
        assertEquals("for post ID must be null", error.getErrorMessage());
    }

    @Test
    void testUpdate_success() {
        final SourcePageModel sourcePage = createSourcePage();

        final SourcePageModel saved = (SourcePageModel) sourcePageService.save(sourcePage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(sourcePage.getOwnerId(), saved.getOwnerId());
        assertEquals(sourcePage.getPageUrl(), saved.getPageUrl());
        assertEquals(sourcePage.getUsername(), saved.getUsername());
        assertEquals(sourcePage.getPassword(), saved.getPassword());

        final String updatedPassword = saved.getPassword() + "-updated";
        saved.setPassword(updatedPassword);

        final SourcePageModel updated = (SourcePageModel) sourcePageService.update(saved);
        assertEquals(updatedPassword, updated.getPassword());
    }

    @Test
    void testUpdate_failure() {
        final SourcePageModel sourcePage = createSourcePage();

        final VersionedModel updated = sourcePageService.update(sourcePage);
        assertEquals("for put ID must not be null", updated.getErrorMessage());
    }

    @Test
    void testGetById_Success() {
        final SourcePageModel sourcePage = createSourcePage();

        final SourcePageModel saved = (SourcePageModel) sourcePageService.save(sourcePage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(sourcePage.getOwnerId(), saved.getOwnerId());
        assertEquals(sourcePage.getPageUrl(), saved.getPageUrl());
        assertEquals(sourcePage.getUsername(), saved.getUsername());
        assertEquals(sourcePage.getPassword(), saved.getPassword());

        final SourcePageModel byId = sourcePageService.getById(saved.getId());
        assertEquals(saved.getId(), byId.getId());
        assertEquals(saved.getOwnerId(), byId.getOwnerId());
        assertEquals(saved.getPageUrl(), byId.getPageUrl());
        assertEquals(saved.getUsername(), byId.getUsername());
        assertEquals(saved.getPassword(), byId.getPassword());
    }

    @Test
    void testGetById_failure() {
        try {
            sourcePageService.getById("X");
        } catch (RuntimeException ex) {
            assertEquals("No Source Page found with this id [X]", ex.getMessage());
        }
    }

    @Test
    void testDelete_success() {
        final SourcePageModel sourcePage = createSourcePage();

        final SourcePageModel saved = (SourcePageModel) sourcePageService.save(sourcePage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(sourcePage.getOwnerId(), saved.getOwnerId());
        assertEquals(sourcePage.getPageUrl(), saved.getPageUrl());
        assertEquals(sourcePage.getUsername(), saved.getUsername());
        assertEquals(sourcePage.getPassword(), saved.getPassword());

        final Boolean deleted = sourcePageService.delete(saved.getId());
        assertTrue(deleted);
    }

    @Test
    void testDelete_failure() {
        try {
            sourcePageService.delete("X");
        } catch (EmptyResultDataAccessException ex) {
            assertEquals("No class "+ SourcePageEntity.class.getName() +" entity with id X exists!", ex.getMessage());
        }
    }

    @Test
    void testList_success() {
        final List<SourcePageModel> list = sourcePageService.list();
        final SourcePageModel sourcePage = createSourcePage();

        final SourcePageModel saved = (SourcePageModel) sourcePageService.save(sourcePage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(sourcePage.getOwnerId(), saved.getOwnerId());
        assertEquals(sourcePage.getPageUrl(), saved.getPageUrl());
        assertEquals(sourcePage.getUsername(), saved.getUsername());
        assertEquals(sourcePage.getPassword(), saved.getPassword());

        final List<SourcePageModel> newList = sourcePageService.list();
        assertEquals(list.size()+1, newList.size());
    }

    private SourcePageModel createSourcePage() {
        AdminModel adminModel = new AdminModel();
        adminModel.setName(RandomStringUtils.randomAlphanumeric(10));
        adminModel.setPassword(RandomStringUtils.randomAlphanumeric(10));

        final AdminModel saved = (AdminModel) adminService.save(adminModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(adminModel.getName(), saved.getName());
        assertEquals(adminModel.getPassword(), saved.getPassword());

        SourcePageModel sourcePageModel = new SourcePageModel();
        sourcePageModel.setOwnerId(saved.getId());
        sourcePageModel.setPageUrl(RandomStringUtils.randomAlphanumeric(20));
        sourcePageModel.setUsername(RandomStringUtils.randomAlphanumeric(15));
        sourcePageModel.setPassword(RandomStringUtils.randomAlphanumeric(15));
        return sourcePageModel;
    }
}
