package com.spring.mediacompaign.dao.models;

import java.util.Arrays;

public class ImageModel extends VersionedModel{

    private byte[] image;

    private String postId;

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
                "image=" + Arrays.toString(image) +
                ", postId='" + postId + '\'' +
                '}';
    }
}
