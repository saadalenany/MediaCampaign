package com.spring.mediacompaign.dao.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostModel extends VersionedModel{

    private String title;

    private LocalDateTime date;

    private String campaignId;

    private List<TextModel> texts = new ArrayList<>();

    private List<ImageModel> images = new ArrayList<>();

    private List<VideoModel> videos = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public List<TextModel> getTexts() {
        return texts;
    }

    public void setTexts(List<TextModel> texts) {
        this.texts = texts;
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }

    public List<VideoModel> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoModel> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", campaignId='" + campaignId + '\'' +
                ", texts=" + texts +
                ", images=" + images +
                ", videos=" + videos +
                '}';
    }
}
