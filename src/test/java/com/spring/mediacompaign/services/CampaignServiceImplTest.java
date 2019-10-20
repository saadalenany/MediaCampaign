package com.spring.mediacompaign.services;

import com.spring.mediacompaign.dao.entities.CampaignEntity;
import com.spring.mediacompaign.dao.models.*;
import com.spring.mediacompaign.services.api.*;
import com.spring.mediacompaign.utils.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CampaignServiceImplTest extends BaseTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SourcePageService sourcePageService;

    @Autowired
    private TargetPageService targetPageService;

    @Autowired
    private SocialPlatformService socialPlatformService;

    @Autowired
    private CampaignService campaignService;

    @Test
    void testSave_success() {
        CampaignModel campaignModel = createCampaign();

        final CampaignModel saved = (CampaignModel) campaignService.save(campaignModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(campaignModel.getName(), saved.getName());
        assertEquals(campaignModel.getActive(), saved.getActive());
        assertEquals(campaignModel.getSourcePageId(), saved.getSourcePageId());
        assertEquals(campaignModel.getTargetPageId(), saved.getTargetPageId());
        assertEquals(campaignModel.getSocialPlatformId(), saved.getSocialPlatformId());
        assertEquals(campaignModel.getPostType(), saved.getPostType());
        assertEquals(campaignModel.getPostThread(), saved.getPostThread());
        assertEquals(campaignModel.getSourceTitle(), saved.getSourceTitle());
    }

    @Test
    void testSave_failure() {
        CampaignModel campaignModel = createCampaign();

        campaignModel.setId(RandomStringUtils.randomAlphanumeric(10));
        final VersionedModel error = campaignService.save(campaignModel);
        assertEquals("for post ID must be null", error.getErrorMessage());
    }

    @Test
    void testUpdate_success() {
        CampaignModel campaignModel = createCampaign();

        CampaignModel saved = (CampaignModel) campaignService.save(campaignModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(campaignModel.getName(), saved.getName());
        assertEquals(campaignModel.getActive(), saved.getActive());
        assertEquals(campaignModel.getSourcePageId(), saved.getSourcePageId());
        assertEquals(campaignModel.getTargetPageId(), saved.getTargetPageId());
        assertEquals(campaignModel.getSocialPlatformId(), saved.getSocialPlatformId());
        assertEquals(campaignModel.getPostType(), saved.getPostType());
        assertEquals(campaignModel.getPostThread(), saved.getPostThread());
        assertEquals(campaignModel.getSourceTitle(), saved.getSourceTitle());

        final String updatedName = saved.getName() + "-updated";
        saved.setName(updatedName);

        final CampaignModel updated = (CampaignModel) campaignService.update(saved);
        assertEquals(updatedName, updated.getName());
    }

    @Test
    void testUpdate_failure() {
        CampaignModel campaignModel = createCampaign();

        final VersionedModel updated = campaignService.update(campaignModel);
        assertEquals("for put ID must not be null", updated.getErrorMessage());
    }

    @Test
    void testGetById_Success() {
        CampaignModel campaignModel = createCampaign();

        CampaignModel saved = (CampaignModel) campaignService.save(campaignModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(campaignModel.getName(), saved.getName());
        assertEquals(campaignModel.getActive(), saved.getActive());
        assertEquals(campaignModel.getSourcePageId(), saved.getSourcePageId());
        assertEquals(campaignModel.getTargetPageId(), saved.getTargetPageId());
        assertEquals(campaignModel.getSocialPlatformId(), saved.getSocialPlatformId());
        assertEquals(campaignModel.getPostType(), saved.getPostType());
        assertEquals(campaignModel.getPostThread(), saved.getPostThread());
        assertEquals(campaignModel.getSourceTitle(), saved.getSourceTitle());

        final CampaignModel byId = campaignService.getById(saved.getId());
        assertEquals(saved.getId(), byId.getId());
        assertEquals(saved.getName(), byId.getName());
        assertEquals(saved.getActive(), byId.getActive());
        assertEquals(saved.getSourcePageId(), byId.getSourcePageId());
        assertEquals(saved.getTargetPageId(), byId.getTargetPageId());
        assertEquals(saved.getSocialPlatformId(), byId.getSocialPlatformId());
        assertEquals(saved.getPostType(), byId.getPostType());
        assertEquals(saved.getPostThread(), byId.getPostThread());
        assertEquals(saved.getSourceTitle(), byId.getSourceTitle());
    }

    @Test
    void testGetById_failure() {
        try {
            campaignService.getById("X");
        } catch (RuntimeException ex) {
            assertEquals("No Campaign found with this id [X]", ex.getMessage());
        }
    }

    @Test
    void testDelete_success() {
        CampaignModel campaignModel = createCampaign();

        CampaignModel saved = (CampaignModel) campaignService.save(campaignModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(campaignModel.getName(), saved.getName());
        assertEquals(campaignModel.getActive(), saved.getActive());
        assertEquals(campaignModel.getSourcePageId(), saved.getSourcePageId());
        assertEquals(campaignModel.getTargetPageId(), saved.getTargetPageId());
        assertEquals(campaignModel.getSocialPlatformId(), saved.getSocialPlatformId());
        assertEquals(campaignModel.getPostType(), saved.getPostType());
        assertEquals(campaignModel.getPostThread(), saved.getPostThread());
        assertEquals(campaignModel.getSourceTitle(), saved.getSourceTitle());

        final Boolean deleted = campaignService.delete(saved.getId());
        assertTrue(deleted);
    }

    @Test
    void testDelete_failure() {
        try {
            campaignService.delete("X");
        } catch (EmptyResultDataAccessException ex) {
            assertEquals("No class " + CampaignEntity.class.getName() + " entity with id X exists!", ex.getMessage());
        }
    }

    @Test
    void testList_success() {
        final List<CampaignModel> list = campaignService.list();
        CampaignModel campaignModel = createCampaign();

        final CampaignModel saved = (CampaignModel) campaignService.save(campaignModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(campaignModel.getName(), saved.getName());
        assertEquals(campaignModel.getActive(), saved.getActive());
        assertEquals(campaignModel.getSourcePageId(), saved.getSourcePageId());
        assertEquals(campaignModel.getTargetPageId(), saved.getTargetPageId());
        assertEquals(campaignModel.getSocialPlatformId(), saved.getSocialPlatformId());
        assertEquals(campaignModel.getPostType(), saved.getPostType());
        assertEquals(campaignModel.getPostThread(), saved.getPostThread());
        assertEquals(campaignModel.getSourceTitle(), saved.getSourceTitle());

        final List<CampaignModel> newList = campaignService.list();
        assertEquals(list.size() + 1, newList.size());
    }


    private CampaignModel createCampaign() {
        //Create an Admin
        AdminModel adminModel = new AdminModel();
        adminModel.setName(RandomStringUtils.randomAlphanumeric(10));
        adminModel.setPassword(RandomStringUtils.randomAlphanumeric(10));

        final AdminModel savedAdmin = (AdminModel) adminService.save(adminModel);

        assertNotNull(savedAdmin);
        assertNotNull(savedAdmin.getId());
        assertEquals(adminModel.getName(), savedAdmin.getName());
        assertEquals(adminModel.getPassword(), savedAdmin.getPassword());

        //Create a Target Page
        TargetPageModel targetPageModel = new TargetPageModel();
        targetPageModel.setOwnerId(savedAdmin.getId());
        targetPageModel.setPageUrl(RandomStringUtils.randomAlphanumeric(20));

        final TargetPageModel savedTarget = (TargetPageModel) targetPageService.save(targetPageModel);
        assertNotNull(savedTarget);
        assertNotNull(savedTarget.getId());
        assertEquals(targetPageModel.getOwnerId(), savedTarget.getOwnerId());
        assertEquals(targetPageModel.getPageUrl(), savedTarget.getPageUrl());

        //Create a Source Page
        SourcePageModel sourcePageModel = new SourcePageModel();
        sourcePageModel.setOwnerId(savedAdmin.getId());
        sourcePageModel.setPageUrl(RandomStringUtils.randomAlphanumeric(20));
        sourcePageModel.setUsername(RandomStringUtils.randomAlphanumeric(15));
        sourcePageModel.setPassword(RandomStringUtils.randomAlphanumeric(15));

        final SourcePageModel savedSource = (SourcePageModel) sourcePageService.save(sourcePageModel);
        assertNotNull(savedSource);
        assertNotNull(savedSource.getId());
        assertEquals(sourcePageModel.getOwnerId(), savedSource.getOwnerId());
        assertEquals(sourcePageModel.getPageUrl(), savedSource.getPageUrl());
        assertEquals(sourcePageModel.getUsername(), savedSource.getUsername());
        assertEquals(sourcePageModel.getPassword(), savedSource.getPassword());

        //Create a Social Platform
        SocialPlatformModel socialPlatformModel = new SocialPlatformModel();
        socialPlatformModel.setAccessToken(RandomStringUtils.randomAlphanumeric(15));
        socialPlatformModel.setAppId(RandomStringUtils.randomAlphanumeric(15));
        socialPlatformModel.setAppSecret(RandomStringUtils.randomAlphanumeric(15));
        socialPlatformModel.setName(RandomStringUtils.randomAlphanumeric(10));
        final SocialPlatformModel savedSocial = (SocialPlatformModel) socialPlatformService.save(socialPlatformModel);

        assertNotNull(savedSocial);
        assertNotNull(savedSocial.getId());
        assertEquals(socialPlatformModel.getName(), savedSocial.getName());
        assertEquals(socialPlatformModel.getAccessToken(), savedSocial.getAccessToken());

        //Create a Campaign
        CampaignModel campaignModel = new CampaignModel();
        campaignModel.setSourcePageId(savedSource.getId());
        campaignModel.setTargetPageId(savedTarget.getId());
        campaignModel.setSocialPlatformId(savedSocial.getId());
        campaignModel.setName(RandomStringUtils.randomAlphanumeric(10));
        campaignModel.setSourceTitle(RandomStringUtils.randomAlphanumeric(12));
        campaignModel.setPostThread(RandomStringUtils.randomAlphanumeric(10));
        campaignModel.setPostType(RandomStringUtils.randomAlphanumeric(10));
        campaignModel.setActive(false);
        campaignModel.setScrapLimitation(10);

        return campaignModel;
    }
}
