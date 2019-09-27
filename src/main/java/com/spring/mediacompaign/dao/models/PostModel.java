package com.spring.mediacompaign.dao.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostModel {

    private String id;

    private String title;

    private LocalDateTime date;

    private CampaignModel campaign;

    private List<TextModel> texts = new ArrayList<>();

    private List<ImageModel> images = new ArrayList<>();

    private List<VideoModel> videos = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public CampaignModel getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignModel campaign) {
        this.campaign = campaign;
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

    public void setImages(List<ImageModel> newImages) {
        this.images.clear();
        if (newImages != null) {
            newImages.forEach(o -> o.setPost(this));
            this.images.addAll(newImages);
        }
    }

    public List<VideoModel> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoModel> newVideos) {
        this.videos.clear();
        if (newVideos != null) {
            newVideos.forEach(o -> o.setPost(this));
            this.videos.addAll(newVideos);
        }
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", campaign=" + campaign +
                ", texts=" + texts +
                ", images=" + images +
                ", videos=" + videos +
                '}';
    }
}
