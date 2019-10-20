package com.spring.mediacompaign.services;

import com.spring.mediacompaign.dao.entities.PostEntity;
import com.spring.mediacompaign.dao.models.*;
import com.spring.mediacompaign.services.api.*;
import com.spring.mediacompaign.utils.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceImplTest extends BaseTest {

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

    @Autowired
    private PostService postService;

    @Test
    void testSave_success() {
        PostModel postModel = createPost();

        final PostModel saved = (PostModel) postService.save(postModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(postModel.getTitle(), saved.getTitle());
        assertEquals(postModel.getDate(), saved.getDate());
    }

    @Test
    void testSave_failure() {
        PostModel postModel = createPost();

        postModel.setId(RandomStringUtils.randomAlphanumeric(10));
        final VersionedModel error = postService.save(postModel);
        assertEquals("for post ID must be null", error.getErrorMessage());
    }

    @Test
    void testUpdate_success() {
        PostModel postModel = createPost();

        PostModel saved = (PostModel) postService.save(postModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(postModel.getTitle(), saved.getTitle());
        assertEquals(postModel.getDate(), saved.getDate());

        final String updatedTitle = saved.getTitle() + "-updated";
        saved.setTitle(updatedTitle);

        final PostModel updated = (PostModel) postService.update(saved);
        assertEquals(updatedTitle, updated.getTitle());
    }

    @Test
    void testUpdate_failure() {
        PostModel postModel = createPost();

        final VersionedModel updated = postService.update(postModel);
        assertEquals("for put ID must not be null", updated.getErrorMessage());
    }

    @Test
    void testGetById_Success() {
        PostModel postModel = createPost();

        PostModel saved = (PostModel) postService.save(postModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(postModel.getTitle(), saved.getTitle());
        assertEquals(postModel.getDate(), saved.getDate());

        final PostModel byId = postService.getById(saved.getId());
        assertEquals(saved.getId(), byId.getId());
        assertEquals(saved.getTitle(), byId.getTitle());
    }

    @Test
    void testGetById_failure() {
        try {
            postService.getById("X");
        } catch (RuntimeException ex) {
            assertEquals("No Post found with this id [X]", ex.getMessage());
        }
    }

    @Test
    void testDelete_success() {
        PostModel postModel = createPost();

        PostModel saved = (PostModel) postService.save(postModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(postModel.getTitle(), saved.getTitle());
        assertEquals(postModel.getDate(), saved.getDate());

        final Boolean deleted = postService.delete(saved.getId());
        assertTrue(deleted);
    }

    @Test
    void testDelete_failure() {
        try {
            postService.delete("X");
        } catch (EmptyResultDataAccessException ex) {
            assertEquals("No class " + PostEntity.class.getName() + " entity with id X exists!", ex.getMessage());
        }
    }

    @Test
    void testList_success() {
        final List<PostModel> list = postService.list();
        PostModel postModel = createPost();

        final PostModel saved = (PostModel) postService.save(postModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(postModel.getTitle(), saved.getTitle());
        assertEquals(postModel.getDate(), saved.getDate());

        final List<PostModel> newList = postService.list();
        assertEquals(list.size() + 1, newList.size());
    }

    private PostModel createPost() {
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
        socialPlatformModel.setName(RandomStringUtils.randomAlphanumeric(10));
        socialPlatformModel.setAppSecret(RandomStringUtils.randomAlphanumeric(10));
        socialPlatformModel.setAppId(RandomStringUtils.randomAlphanumeric(10));
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
        campaignModel.setNop(10);
        campaignModel.setPer("hour");
        campaignModel.setPostType(RandomStringUtils.randomAlphanumeric(10));
        campaignModel.setActive(false);
        campaignModel.setScrapLimitation(10);

        final CampaignModel savedCampaign = (CampaignModel) campaignService.save(campaignModel);

        assertNotNull(savedCampaign);
        assertNotNull(savedCampaign.getId());
        assertEquals(campaignModel.getName(), savedCampaign.getName());
        assertEquals(campaignModel.getActive(), savedCampaign.getActive());
        assertEquals(campaignModel.getSourcePageId(), savedCampaign.getSourcePageId());
        assertEquals(campaignModel.getTargetPageId(), savedCampaign.getTargetPageId());
        assertEquals(campaignModel.getSocialPlatformId(), savedCampaign.getSocialPlatformId());
        assertEquals(campaignModel.getPostType(), savedCampaign.getPostType());
        assertEquals(campaignModel.getNop(), savedCampaign.getNop());
        assertEquals(campaignModel.getPer(), savedCampaign.getPer());
        assertEquals(campaignModel.getSourceTitle(), savedCampaign.getSourceTitle());

        //Create a Post
        PostModel postModel = new PostModel();
        postModel.setCampaignId(savedCampaign.getId());
        postModel.setTitle(RandomStringUtils.randomAlphanumeric(15));
        postModel.setDate(LocalDateTime.now());

        return postModel;
    }

}
