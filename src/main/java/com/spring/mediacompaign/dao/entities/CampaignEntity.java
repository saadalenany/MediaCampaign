package com.spring.mediacompaign.dao.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campaign")
public class CampaignEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "source_page_id")
    private SourcePageEntity sourcePage;

    @ManyToOne
    @JoinColumn(name = "target_page_id")
    private TargetPageEntity targetPage;

    @ManyToOne
    @JoinColumn(name = "social_platform_id")
    private SocialPlatformEntity socialPlatform;

    @Column(name = "source_title")
    private String sourceTitle;

    @Column(name = "source_with_ornot")
    private Boolean sourceWithOrnot;

    @Column(name = "scrap_limitation")
    private Integer scrapLimitation;

    @Column(name = "post_thread")
    private String postThread;

    @Column(name = "post_type")
    private String postType;

    @Column(name = "block_keywords")
    private String blockKeywords;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostEntity> posts = new ArrayList<>();

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

    public SourcePageEntity getSourcePage() {
        return sourcePage;
    }

    public void setSourcePage(SourcePageEntity sourcePage) {
        this.sourcePage = sourcePage;
    }

    public TargetPageEntity getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(TargetPageEntity targetPage) {
        this.targetPage = targetPage;
    }

    public SocialPlatformEntity getSocialPlatform() {
        return socialPlatform;
    }

    public void setSocialPlatform(SocialPlatformEntity socialPlatform) {
        this.socialPlatform = socialPlatform;
    }

    public String getSourceTitle() {
        return sourceTitle;
    }

    public void setSourceTitle(String sourceTitle) {
        this.sourceTitle = sourceTitle;
    }

    public Boolean getSourceWithOrnot() {
        return sourceWithOrnot;
    }

    public void setSourceWithOrnot(Boolean sourceWithOrnot) {
        this.sourceWithOrnot = sourceWithOrnot;
    }

    public Integer getScrapLimitation() {
        return scrapLimitation;
    }

    public void setScrapLimitation(Integer scrapLimitation) {
        this.scrapLimitation = scrapLimitation;
    }

    public String getPostThread() {
        return postThread;
    }

    public void setPostThread(String postThread) {
        this.postThread = postThread;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getBlockKeywords() {
        return blockKeywords;
    }

    public void setBlockKeywords(String blockKeywords) {
        this.blockKeywords = blockKeywords;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> newPosts) {
        this.posts.clear();
        if (newPosts != null) {
            newPosts.forEach(o -> o.setCampaign(this));
            this.posts.addAll(newPosts);
        }
    }
}