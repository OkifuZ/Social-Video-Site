package com.db2020prj.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`like`")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@EntityListeners(AuditingEntityListener.class)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "like_id")
    int id;

    ReferenceContentType likedType;

    @CreatedDate
    Date likingDate;

    @ManyToOne
    @JoinColumn(name = "liker_id")
    User liker;

    @ManyToOne
    @JoinColumn(name = "liked_column_id")
    Column likedColumn;

    @ManyToOne
    @JoinColumn(name = "liked_bangumi_id")
    Bangumi likedBangumi;

    @ManyToOne
    @JoinColumn(name = "liked_video_id")
    Video likedVideo;

    @ManyToOne
    @JoinColumn(name = "liked_dynamic_id")
    Dynamic likedDynamic;

    @ManyToOne
    @JoinColumn(name = "liked_comment_id")
    Comment likedComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReferenceContentType getLikedType() {
        return likedType;
    }

    public void setLikedType(ReferenceContentType likedType) {
        this.likedType = likedType;
    }

    public Date getLikingDate() {
        return likingDate;
    }

    public void setLikingDate(Date likingDate) {
        this.likingDate = likingDate;
    }

    public User getLiker() {
        return liker;
    }

    public void setLiker(User liker) {
        this.liker = liker;
    }

    public Column getLikedColumn() {
        return likedColumn;
    }

    public void setLikedColumn(Column likedColumn) {
        this.likedColumn = likedColumn;
    }

    public Bangumi getLikedBangumi() {
        return likedBangumi;
    }

    public void setLikedBangumi(Bangumi likedBangumi) {
        this.likedBangumi = likedBangumi;
    }

    public Video getLikedVideo() {
        return likedVideo;
    }

    public void setLikedVideo(Video likedVideo) {
        this.likedVideo = likedVideo;
    }

    public Dynamic getLikedDynamic() {
        return likedDynamic;
    }

    public void setLikedDynamic(Dynamic likedDynamic) {
        this.likedDynamic = likedDynamic;
    }

    public Comment getLikedComment() {
        return likedComment;
    }

    public void setLikedComment(Comment likedComment) {
        this.likedComment = likedComment;
    }
}

