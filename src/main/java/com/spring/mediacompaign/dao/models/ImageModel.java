package com.spring.mediacompaign.dao.models;

import java.util.Arrays;

public class ImageModel {

    private String id;

    private byte[] image;

    private String postId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "id='" + id + '\'' +
                ", image=" + Arrays.toString(image) +
                ", postId='" + postId + '\'' +
                '}';
    }
}
