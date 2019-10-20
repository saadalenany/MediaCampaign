package com.spring.mediacompaign.services;

import com.spring.mediacompaign.dao.entities.VideoEntity;
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

class VideoServiceImplTest extends BaseTest {

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

    @Autowired
    private VideoService videoService;

    @Test
    void testSave_success() {
        VideoModel videoModel = createVideo();

        final VideoModel saved = (VideoModel) videoService.save(videoModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(videoModel.getVideo().length, saved.getVideo().length);
        assertEquals(videoModel.getPostId(), saved.getPostId());
    }

    @Test
    void testSave_failure() {
        VideoModel videoModel = createVideo();

        videoModel.setId(RandomStringUtils.randomAlphanumeric(10));
        final VersionedModel error = videoService.save(videoModel);
        assertEquals("for post ID must be null", error.getErrorMessage());
    }

    @Test
    void testUpdate_success() {
        VideoModel videoModel = createVideo();

        VideoModel saved = (VideoModel) videoService.save(videoModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(videoModel.getVideo().length, saved.getVideo().length);
        assertEquals(videoModel.getPostId(), saved.getPostId());

        final byte[] updatedVideo = new byte[12];
        saved.setVideo(updatedVideo);

        final VideoModel updated = (VideoModel) videoService.update(saved);
        assertEquals(updatedVideo.length, updated.getVideo().length);
    }

    @Test
    void testUpdate_failure() {
        VideoModel videoModel = createVideo();

        final VersionedModel updated = videoService.update(videoModel);
        assertEquals("for put ID must not be null", updated.getErrorMessage());
    }

    @Test
    void testGetById_Success() {
        VideoModel videoModel = createVideo();

        VideoModel saved = (VideoModel) videoService.save(videoModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(videoModel.getVideo().length, saved.getVideo().length);
        assertEquals(videoModel.getPostId(), saved.getPostId());

        final VideoModel byId = videoService.getById(saved.getId());
        assertEquals(saved.getId(), byId.getId());
        assertEquals(saved.getVideo().length, byId.getVideo().length);
    }

    @Test
    void testGetById_failure() {
        try {
            videoService.getById("X");
        } catch (RuntimeException ex) {
            assertEquals("No Video found with this id [X]", ex.getMessage());
        }
    }

    @Test
    void testDelete_success() {
        VideoModel videoModel = createVideo();

        VideoModel saved = (VideoModel) videoService.save(videoModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(videoModel.getVideo().length, saved.getVideo().length);
        assertEquals(videoModel.getPostId(), saved.getPostId());

        final Boolean deleted = videoService.delete(saved.getId());
        assertTrue(deleted);
    }

    @Test
    void testDelete_failure() {
        try {
            videoService.delete("X");
        } catch (EmptyResultDataAccessException ex) {
            assertEquals("No class " + VideoEntity.class.getName() + " entity with id X exists!", ex.getMessage());
        }
    }

    @Test
    void testList_success() {
        final List<VideoModel> list = videoService.list();
        VideoModel videoModel = createVideo();

        final VideoModel saved = (VideoModel) videoService.save(videoModel);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(videoModel.getVideo().length, saved.getVideo().length);
        assertEquals(videoModel.getPostId(), saved.getPostId());

        final List<VideoModel> newList = videoService.list();
        assertEquals(list.size() + 1, newList.size());
    }

    private VideoModel createVideo() {
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
        campaignModel.setPostThread(RandomStringUtils.randomAlphanumeric(10));
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
        assertEquals(campaignModel.getPostThread(), savedCampaign.getPostThread());
        assertEquals(campaignModel.getSourceTitle(), savedCampaign.getSourceTitle());

        //Create a Post
        PostModel postModel = new PostModel();
        postModel.setCampaignId(savedCampaign.getId());
        postModel.setTitle(RandomStringUtils.randomAlphanumeric(15));
        postModel.setDate(LocalDateTime.now());

        final PostModel savedPost = (PostModel) postService.save(postModel);

        assertNotNull(savedPost);
        assertNotNull(savedPost.getId());
        assertEquals(postModel.getTitle(), savedPost.getTitle());
        assertEquals(postModel.getDate(), savedPost.getDate());

        VideoModel videoModel = new VideoModel();
        videoModel.setPostId(savedPost.getId());
        videoModel.setVideo(new byte[10]);
        return videoModel;
    }
}