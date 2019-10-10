package com.spring.mediacompaign.dao.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "social_platform")
public class SocialPlatformEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "app_secret")
    private String appSecret;

    @OneToMany(mappedBy = "socialPlatform", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CampaignEntity> campaigns = new ArrayList<>();

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public List<CampaignEntity> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignEntity> newCampaigns) {
        this.campaigns.clear();
        if (newCampaigns != null) {
            newCampaigns.forEach(o -> o.setSocialPlatform(this));
            this.campaigns.addAll(newCampaigns);
        }
    }
}
