package com.spring.mediacompaign.dao.models;

import java.util.Arrays;

public class ImageModel {

    private String id;

    private byte[] image;

    private PostModel post;

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

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "id='" + id + '\'' +
                ", image=" + Arrays.toString(image) +
                ", post=" + post +
                '}';
    }
}
