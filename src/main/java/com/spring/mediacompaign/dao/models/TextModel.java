package com.spring.mediacompaign.dao.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

public class TextModel {

    private String id;

    private String content;

    private PostModel post;

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

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "TextModel{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", post=" + post +
                '}';
    }
}
