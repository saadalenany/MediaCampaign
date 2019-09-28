package com.spring.mediacompaign.dao.models;

public class TextModel {

    private String id;

    private String content;

    private String postId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "TextModel{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", postId='" + postId + '\'' +
                '}';
    }
}
