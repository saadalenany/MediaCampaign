package com.spring.mediacompaign.dao.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SourcePageEntity> sourcePages = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TargetPageEntity> targetPages = new ArrayList<>();

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

    public List<SourcePageEntity> getSourcePages() {
        return sourcePages;
    }

    public void setSourcePages(List<SourcePageEntity> newSourcePages) {
        this.sourcePages.clear();
        if (newSourcePages != null) {
            newSourcePages.forEach(o -> o.setOwner(this));
            this.sourcePages.addAll(newSourcePages);
        }
    }

    public List<TargetPageEntity> getTargetPages() {
        return targetPages;
    }

    public void setTargetPages(List<TargetPageEntity> newTargetPages) {
        this.targetPages.clear();
        if (newTargetPages != null) {
            newTargetPages.forEach(o -> o.setOwner(this));
            this.targetPages.addAll(newTargetPages);
        }
    }
}
