package com.spring.mediacompaign.dao.models;

import java.util.ArrayList;
import java.util.List;

public class SocialPlatformModel {

    private String id;

    private String name;

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

    public List<CampaignModel> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignModel> newCampaigns) {
        this.campaigns.clear();
        if (newCampaigns != null) {
            newCampaigns.forEach(o -> o.setSocialPlatform(this));
            this.campaigns.addAll(newCampaigns);
        }
    }

    @Override
    public String toString() {
        return "SocialPlatformModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", campaigns=" + campaigns +
                '}';
    }
}
