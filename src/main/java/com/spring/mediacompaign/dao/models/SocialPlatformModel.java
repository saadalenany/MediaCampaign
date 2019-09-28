package com.spring.mediacompaign.dao.models;

import java.util.ArrayList;
import java.util.List;

public class SocialPlatformModel {

    private String id;

    private String name;

    private String accessToken;

    private List<CampaignModel> campaigns = new ArrayList<>();

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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<CampaignModel> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignModel> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public String toString() {
        return "SocialPlatformModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", campaigns=" + campaigns +
                '}';
    }
}
