package com.spring.mediacompaign.dao.models;

import java.util.ArrayList;
import java.util.List;

public class AdminModel {

    private String id;

    private String name;

    private String password;

    private List<SourcePageModel> sourcePages = new ArrayList<>();

    private List<TargetPageModel> targetPages = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SourcePageModel> getSourcePages() {
        return sourcePages;
    }

    public void setSourcePages(List<SourcePageModel> newSourcePages) {
        this.sourcePages.clear();
        if (newSourcePages != null) {
            newSourcePages.forEach(o -> o.setOwner(this));
            this.sourcePages.addAll(newSourcePages);
        }
    }

    public List<TargetPageModel> getTargetPages() {
        return targetPages;
    }

    public void setTargetPages(List<TargetPageModel> newTargetPages) {
        this.targetPages.clear();
        if (newTargetPages != null) {
            newTargetPages.forEach(o -> o.setOwner(this));
            this.targetPages.addAll(newTargetPages);
        }
    }

    @Override
    public String toString() {
        return "AdminModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sourcePages=" + sourcePages +
                ", targetPages=" + targetPages +
                '}';
    }
}
