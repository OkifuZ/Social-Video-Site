package com.db2020prj.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "favorite_content")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@EntityListeners(AuditingEntityListener.class)
public class FavoriteContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "fc_id")
    int id;

    @CreatedDate
    Date fcCollectedDate;

    ReferenceContentType fcType;

    @ManyToOne
    @JoinColumn(name = "fc_owner_id")
    Favorite fcOwner;

    @ManyToOne
    @JoinColumn(name = "fc_column_id")
    Column fcColumn;

    @ManyToOne
    @JoinColumn(name = "fc_bangumi_id")
    Bangumi fcBangumi;

    @ManyToOne
    @JoinColumn(name = "fc_video_id")
    Video fcVideo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFcCollectedDate() {
        return fcCollectedDate;
    }

    public void setFcCollectedDate(Date fcCollectedDate) {
        this.fcCollectedDate = fcCollectedDate;
    }

    public ReferenceContentType getFcType() {
        return fcType;
    }

    public void setFcType(ReferenceContentType fcType) {
        this.fcType = fcType;
    }

    public Favorite getFcOwner() {
        return fcOwner;
    }

    public void setFcOwner(Favorite fcOwner) {
        this.fcOwner = fcOwner;
    }

    public Column getFcColumn() {
        return fcColumn;
    }

    public void setFcColumn(Column fcColumn) {
        this.fcColumn = fcColumn;
    }

    public Bangumi getFcBangumi() {
        return fcBangumi;
    }

    public void setFcBangumi(Bangumi fcBangumi) {
        this.fcBangumi = fcBangumi;
    }

    public Video getFcVideo() {
        return fcVideo;
    }

    public void setFcVideo(Video fcVideo) {
        this.fcVideo = fcVideo;
    }
}
