package com.spring.mediacompaign.dao.models;

import java.util.ArrayList;
import java.util.List;

public class SourcePageModel extends VersionedModel{

    private String pageUrl;

    private String ownerId;

    private String username;

    private String password;

    private List<CampaignModel> campaigns = new ArrayList<>();

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CampaignModel> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignModel> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public String toString() {
        return "SourcePageModel{" +
                "pageUrl='" + pageUrl + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", campaigns=" + campaigns +
                '}';
    }
}
