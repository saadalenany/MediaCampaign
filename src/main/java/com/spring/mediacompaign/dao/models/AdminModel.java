package com.spring.mediacompaign.dao.models;

import java.util.ArrayList;
import java.util.List;

public class AdminModel extends VersionedModel{

    private String name;

    private String password;

    private List<SourcePageModel> sourcePages = new ArrayList<>();

    private List<TargetPageModel> targetPages = new ArrayList<>();

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

    public void setSourcePages(List<SourcePageModel> sourcePages) {
        this.sourcePages = sourcePages;
    }

    public List<TargetPageModel> getTargetPages() {
        return targetPages;
    }

    public void setTargetPages(List<TargetPageModel> targetPages) {
        this.targetPages = targetPages;
    }

    @Override
    public String toString() {
        return "AdminModel{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sourcePages=" + sourcePages +
                ", targetPages=" + targetPages +
                '}';
    }
}
