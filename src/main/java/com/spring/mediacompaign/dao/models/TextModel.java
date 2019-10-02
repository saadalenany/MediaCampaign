package com.spring.mediacompaign.dao.models;

public class TextModel extends VersionedModel{

    private String content;

    private String postId;

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
                "content='" + content + '\'' +
                ", postId='" + postId + '\'' +
                '}';
    }
}
