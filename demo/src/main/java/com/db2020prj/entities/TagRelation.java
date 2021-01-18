package com.db2020prj.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tag_relation")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class TagRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "tag_relation_id")
    int id;

    ReferenceContentType taggedType;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    Tag tag;

    @ManyToOne
    @JoinColumn(name = "tagged_column_id")
    Column taggedColumn;

    @ManyToOne
    @JoinColumn(name = "tagged_bangumi_id")
    Bangumi taggedBangumi;

    @ManyToOne
    @JoinColumn(name = "tagged_video_id")
    Video taggedVideo;

    @ManyToOne
    @JoinColumn(name = "tagged_dynamic_id")
    Dynamic taggedDynamic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReferenceContentType getTaggedType() {
        return taggedType;
    }

    public void setTaggedType(ReferenceContentType taggedType) {
        this.taggedType = taggedType;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Column getTaggedColumn() {
        return taggedColumn;
    }

    public void setTaggedColumn(Column taggedColumn) {
        this.taggedColumn = taggedColumn;
    }

    public Bangumi getTaggedBangumi() {
        return taggedBangumi;
    }

    public void setTaggedBangumi(Bangumi taggedBangumi) {
        this.taggedBangumi = taggedBangumi;
    }

    public Video getTaggedVideo() {
        return taggedVideo;
    }

    public void setTaggedVideo(Video taggedVideo) {
        this.taggedVideo = taggedVideo;
    }

    public Dynamic getTaggedDynamic() {
        return taggedDynamic;
    }

    public void setTaggedDynamic(Dynamic taggedDynamic) {
        this.taggedDynamic = taggedDynamic;
    }
}
