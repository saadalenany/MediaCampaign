package com.spring.mediacompaign.services;

import com.spring.mediacompaign.dao.entities.TargetPageEntity;
import com.spring.mediacompaign.dao.models.AdminModel;
import com.spring.mediacompaign.dao.models.TargetPageModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.services.api.AdminService;
import com.spring.mediacompaign.services.api.TargetPageService;
import com.spring.mediacompaign.utils.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TargetPageServiceImplTest extends BaseTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TargetPageService targetPageService;

    @Test
    void testSave_success() {
        final TargetPageModel targetPage = createTargetPage();

        final TargetPageModel saved = (TargetPageModel) targetPageService.save(targetPage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(targetPage.getOwnerId(), saved.getOwnerId());
        assertEquals(targetPage.getPageUrl(), saved.getPageUrl());
    }

    @Test
    void testSave_failure() {
        final TargetPageModel targetPage = createTargetPage();

        final TargetPageModel saved = (TargetPageModel) targetPageService.save(targetPage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(targetPage.getOwnerId(), saved.getOwnerId());
        assertEquals(targetPage.getPageUrl(), saved.getPageUrl());

        final VersionedModel error = targetPageService.save(saved);
        assertEquals("for post ID must be null", error.getErrorMessage());
    }

    @Test
    void testUpdate_success() {
        final TargetPageModel targetPage = createTargetPage();

        final TargetPageModel saved = (TargetPageModel) targetPageService.save(targetPage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(targetPage.getOwnerId(), saved.getOwnerId());
        assertEquals(targetPage.getPageUrl(), saved.getPageUrl());

        final String updatedPageUrl = saved.getPageUrl() + "-updated";
        saved.setPageUrl(updatedPageUrl);

        final TargetPageModel updated = (TargetPageModel) targetPageService.update(saved);
        assertEquals(updatedPageUrl, updated.getPageUrl());
    }

    @Test
    void testUpdate_failure() {
        final TargetPageModel targetPage = createTargetPage();

        final VersionedModel updated = targetPageService.update(targetPage);
        assertEquals("for put ID must not be null", updated.getErrorMessage());
    }

    @Test
    void testGetById_Success() {
        final TargetPageModel targetPage = createTargetPage();

        final TargetPageModel saved = (TargetPageModel) targetPageService.save(targetPage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(targetPage.getOwnerId(), saved.getOwnerId());
        assertEquals(targetPage.getPageUrl(), saved.getPageUrl());

        final TargetPageModel byId = targetPageService.getById(saved.getId());
        assertEquals(saved.getId(), byId.getId());
        assertEquals(saved.getOwnerId(), byId.getOwnerId());
        assertEquals(saved.getPageUrl(), byId.getPageUrl());
    }

    @Test
    void testGetById_failure() {
        try {
            targetPageService.getById("X");
        } catch (RuntimeException ex) {
            assertEquals("No Target Page found with this id [X]", ex.getMessage());
        }
    }

    @Test
    void testDelete_success() {
        final TargetPageModel targetPage = createTargetPage();

        final TargetPageModel saved = (TargetPageModel) targetPageService.save(targetPage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(targetPage.getOwnerId(), saved.getOwnerId());
        assertEquals(targetPage.getPageUrl(), saved.getPageUrl());

        final Boolean deleted = targetPageService.delete(saved.getId());
        assertTrue(deleted);
    }

    @Test
    void testDelete_failure() {
        try {
            targetPageService.delete("X");
        } catch (EmptyResultDataAccessException ex) {
            assertEquals("No class " + TargetPageEntity.class.getName() + " entity with id X exists!", ex.getMessage());
        }
    }

    @Test
    void testList_success() {
        final List<TargetPageModel> list = targetPageService.list();
        final TargetPageModel targetPage = createTargetPage();

        final TargetPageModel saved = (TargetPageModel) targetPageService.save(targetPage);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(targetPage.getOwnerId(), saved.getOwnerId());
        assertEquals(targetPage.getPageUrl(), saved.getPageUrl());

        final List<TargetPageModel> newList = targetPageService.list();
        assertEquals(list.size() + 1, newList.size());
    }

    private TargetPageModel createTargetPage() {
        AdminModel adminModel = new AdminModel();
        adminModel.setName(RandomStringUtils.randomAlphanumeric(10));
        adminModel.setPassword(RandomStringUtils.randomAlphanumeric(10));

        final AdminModel saved = (AdminModel) adminService.save(adminModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(adminModel.getName(), saved.getName());
        assertEquals(adminModel.getPassword(), saved.getPassword());

        TargetPageModel targetPageModel = new TargetPageModel();
        targetPageModel.setOwnerId(saved.getId());
        targetPageModel.setPageUrl(RandomStringUtils.randomAlphanumeric(20));
        return targetPageModel;
    }
}
