package com.spring.mediacompaign.services;

import com.spring.mediacompaign.dao.entities.SocialPlatformEntity;
import com.spring.mediacompaign.dao.models.SocialPlatformModel;
import com.spring.mediacompaign.dao.models.VersionedModel;
import com.spring.mediacompaign.services.api.SocialPlatformService;
import com.spring.mediacompaign.utils.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SocialPlatformServiceImplTest extends BaseTest {

    @Autowired
    private SocialPlatformService socialPlatformService;

    @Test
    void testSave_success() {
        final SocialPlatformModel socialPlatformModel = createSocialPlatform();

        final SocialPlatformModel saved = (SocialPlatformModel) socialPlatformService.save(socialPlatformModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(socialPlatformModel.getName(), saved.getName());
        assertEquals(socialPlatformModel.getAccessToken(), saved.getAccessToken());
    }

    @Test
    void testSave_failure() {
        final SocialPlatformModel socialPlatformModel = createSocialPlatform();

        socialPlatformModel.setId(RandomStringUtils.randomAlphanumeric(10));
        final VersionedModel error = socialPlatformService.save(socialPlatformModel);
        assertEquals("for post ID must be null", error.getErrorMessage());
    }

    @Test
    void testUpdate_success() {
        final SocialPlatformModel socialPlatformModel = createSocialPlatform();

        final SocialPlatformModel saved = (SocialPlatformModel) socialPlatformService.save(socialPlatformModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(socialPlatformModel.getName(), saved.getName());
        assertEquals(socialPlatformModel.getAccessToken(), saved.getAccessToken());

        final String updatedName = saved.getName() + "-updated";
        saved.setName(updatedName);

        final SocialPlatformModel updated = (SocialPlatformModel) socialPlatformService.update(saved);
        assertEquals(updatedName, updated.getName());
    }

    @Test
    void testUpdate_failure() {
        final SocialPlatformModel socialPlatformModel = createSocialPlatform();

        final VersionedModel updated = socialPlatformService.update(socialPlatformModel);
        assertEquals("for put ID must not be null", updated.getErrorMessage());
    }

    @Test
    void testGetById_Success() {
        final SocialPlatformModel socialPlatformModel = createSocialPlatform();

        final SocialPlatformModel saved = (SocialPlatformModel) socialPlatformService.save(socialPlatformModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(socialPlatformModel.getName(), saved.getName());
        assertEquals(socialPlatformModel.getAccessToken(), saved.getAccessToken());

        final SocialPlatformModel byId = socialPlatformService.getById(saved.getId());
        assertEquals(saved.getId(), byId.getId());
        assertEquals(saved.getName(), byId.getName());
        assertEquals(saved.getAccessToken(), byId.getAccessToken());
    }

    @Test
    void testGetById_failure() {
        try {
            socialPlatformService.getById("X");
        } catch (RuntimeException ex) {
            assertEquals("No SocialPlatform found with this id [X]", ex.getMessage());
        }
    }

    @Test
    void testDelete_success() {
        final SocialPlatformModel socialPlatformModel = createSocialPlatform();

        final SocialPlatformModel saved = (SocialPlatformModel) socialPlatformService.save(socialPlatformModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(socialPlatformModel.getName(), saved.getName());
        assertEquals(socialPlatformModel.getAccessToken(), saved.getAccessToken());

        final Boolean deleted = socialPlatformService.delete(saved.getId());
        assertTrue(deleted);
    }

    @Test
    void testDelete_failure() {
        try {
            socialPlatformService.delete("X");
        } catch (EmptyResultDataAccessException ex) {
            assertEquals("No class " + SocialPlatformEntity.class.getName() + " entity with id X exists!", ex.getMessage());
        }
    }

    @Test
    void testList_success() {
        final List<SocialPlatformModel> list = socialPlatformService.list();
        SocialPlatformModel socialPlatformModel = createSocialPlatform();

        final SocialPlatformModel saved = (SocialPlatformModel) socialPlatformService.save(socialPlatformModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(socialPlatformModel.getName(), saved.getName());
        assertEquals(socialPlatformModel.getAccessToken(), saved.getAccessToken());

        final List<SocialPlatformModel> newList = socialPlatformService.list();
        assertEquals(list.size() + 1, newList.size());
    }

    private SocialPlatformModel createSocialPlatform() {
        SocialPlatformModel socialPlatformModel = new SocialPlatformModel();
        socialPlatformModel.setName(RandomStringUtils.randomAlphanumeric(10));
        socialPlatformModel.setAccessToken(RandomStringUtils.randomAlphanumeric(128));
        socialPlatformModel.setAppId(RandomStringUtils.randomAlphanumeric(36));
        socialPlatformModel.setAppSecret(RandomStringUtils.randomAlphanumeric(36));
        return socialPlatformModel;
    }
}
