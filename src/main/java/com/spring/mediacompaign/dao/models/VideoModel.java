package com.spring.mediacompaign.dao.models;

import java.util.Arrays;

public class VideoModel {

    private String id;

    private byte[] video;

    private String postId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "VideoModel{" +
                "id='" + id + '\'' +
                ", video=" + Arrays.toString(video) +
                ", postId='" + postId + '\'' +
                '}';
    }
}
