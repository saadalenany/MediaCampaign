package com.spring.mediacompaign.dao.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "target_page")
public class TargetPageEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "page_url")
    private String pageUrl;

    @ManyToOne
    @JoinColumn(name = "owner")
    private AdminEntity owner;

    @OneToMany(mappedBy = "targetPage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CampaignEntity> campaigns = new ArrayList<>();

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

    public AdminEntity getOwner() {
        return owner;
    }

    public void setOwner(AdminEntity owner) {
        this.owner = owner;
    }

    public List<CampaignEntity> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignEntity> newCampaigns) {
        this.campaigns.clear();
        if (newCampaigns != null) {
            newCampaigns.forEach(o -> o.setTargetPage(this));
            this.campaigns.addAll(newCampaigns);
        }
    }
}
