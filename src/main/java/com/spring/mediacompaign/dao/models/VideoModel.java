package com.spring.mediacompaign.dao.models;

import java.util.Arrays;

public class VideoModel extends VersionedModel{

    private byte[] video;

    private String postId;

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
                "video=" + Arrays.toString(video) +
                ", postId='" + postId + '\'' +
                '}';
    }
}
