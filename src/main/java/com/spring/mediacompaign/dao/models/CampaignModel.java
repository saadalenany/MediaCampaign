package com.spring.mediacompaign.dao.models;

import java.util.ArrayList;
import java.util.List;

public class CampaignModel {

    private String id;

    private String name;

    private String sourcePageId;

    private String targetPageId;

    private String socialPlatformId;

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

    public String getSourcePageId() {
        return sourcePageId;
    }

    public void setSourcePageId(String sourcePageId) {
        this.sourcePageId = sourcePageId;
    }

    public String getTargetPageId() {
        return targetPageId;
    }

    public void setTargetPageId(String targetPageId) {
        this.targetPageId = targetPageId;
    }

    public String getSocialPlatformId() {
        return socialPlatformId;
    }

    public void setSocialPlatformId(String socialPlatformId) {
        this.socialPlatformId = socialPlatformId;
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

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "CampaignModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sourcePageId='" + sourcePageId + '\'' +
                ", targetPageId='" + targetPageId + '\'' +
                ", socialPlatformId='" + socialPlatformId + '\'' +
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