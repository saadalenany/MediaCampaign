package com.spring.mediacompaign.dao.models;

import java.util.ArrayList;
import java.util.List;

public class TargetPageModel {

    private String id;

    private String pageUrl;

    private String ownerId;

    private List<CampaignModel> campaigns = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<CampaignModel> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignModel> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public String toString() {
        return "TargetPageModel{" +
                "id='" + id + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", campaigns=" + campaigns +
                '}';
    }
}
