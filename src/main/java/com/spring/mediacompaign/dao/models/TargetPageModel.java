package com.spring.mediacompaign.dao.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class TargetPageModel {

    private String id;

    private String pageUrl;

    private AdminModel owner;

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

    public AdminModel getOwner() {
        return owner;
    }

    public void setOwner(AdminModel owner) {
        this.owner = owner;
    }

    public List<CampaignModel> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignModel> newCampaigns) {
        this.campaigns.clear();
        if (newCampaigns != null) {
            newCampaigns.forEach(o -> o.setTargetPage(this));
            this.campaigns.addAll(newCampaigns);
        }
    }

    @Override
    public String toString() {
        return "TargetPageModel{" +
                "id='" + id + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", owner=" + owner +
                ", campaigns=" + campaigns +
                '}';
    }
}
