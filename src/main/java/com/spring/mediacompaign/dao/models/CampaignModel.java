package com.spring.mediacompaign.dao.models;

import java.util.ArrayList;
import java.util.List;

public class CampaignModel {

    private String id;

    private String name;

    private SourcePageModel sourcePage;

    private TargetPageModel targetPage;

    private SocialPlatformModel socialPlatform;

    private String sourceTitle;

    private Boolean sourceWithOrnot;

    private Integer scrapLimitation;

    private String postThread;

    private String postType;

    private String blockKeywords;

    private Boolean active;

    private List<PostModel> posts = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SourcePageModel getSourcePage() {
        return sourcePage;
    }

    public void setSourcePage(SourcePageModel sourcePage) {
        this.sourcePage = sourcePage;
    }

    public TargetPageModel getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(TargetPageModel targetPage) {
        this.targetPage = targetPage;
    }

    public SocialPlatformModel getSocialPlatform() {
        return socialPlatform;
    }

    public void setSocialPlatform(SocialPlatformModel socialPlatform) {
        this.socialPlatform = socialPlatform;
    }

    public String getSourceTitle() {
        return sourceTitle;
    }

    public void setSourceTitle(String sourceTitle) {
        this.sourceTitle = sourceTitle;
    }

    public Boolean getSourceWithOrnot() {
        return sourceWithOrnot;
    }

    public void setSourceWithOrnot(Boolean sourceWithOrnot) {
        this.sourceWithOrnot = sourceWithOrnot;
    }

    public Integer getScrapLimitation() {
        return scrapLimitation;
    }

    public void setScrapLimitation(Integer scrapLimitation) {
        this.scrapLimitation = scrapLimitation;
    }

    public String getPostThread() {
        return postThread;
    }

    public void setPostThread(String postThread) {
        this.postThread = postThread;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getBlockKeywords() {
        return blockKeywords;
    }

    public void setBlockKeywords(String blockKeywords) {
        this.blockKeywords = blockKeywords;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModel> newPosts) {
        this.posts.clear();
        if (newPosts != null) {
            newPosts.forEach(o -> o.setCampaign(this));
            this.posts.addAll(newPosts);
        }
    }

    @Override
    public String toString() {
        return "CampaignModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sourcePage=" + sourcePage +
                ", targetPage=" + targetPage +
                ", socialPlatform=" + socialPlatform +
                ", sourceTitle='" + sourceTitle + '\'' +
                ", sourceWithOrnot=" + sourceWithOrnot +
                ", scrapLimitation=" + scrapLimitation +
                ", postThread='" + postThread + '\'' +
                ", postType='" + postType + '\'' +
                ", blockKeywords='" + blockKeywords + '\'' +
                ", active=" + active +
                ", posts=" + posts +
                '}';
    }
}