package com.db2020prj.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "comment_id")
    int id;

    @CreatedDate
    Date commentSendDate;

    String commentContent;
    ReferenceContentType commentedType;
    Integer commentLikeNumbers;

    public Integer getCommentLikeNumbers() {
        return commentLikeNumbers;
    }

    public void setCommentLikeNumbers(Integer commentLikeNumbers) {
        this.commentLikeNumbers = commentLikeNumbers;
    }

    @ManyToOne
    @JoinColumn(name = "comment_sender_id")
    User commentSender;

    @ManyToOne
    @JoinColumn(name = "commented_bangumi_id")
    Bangumi commentedBangumi;

    @ManyToOne
    @JoinColumn(name = "commented_video_id")
    Video commentedVideo;

    @ManyToOne
    @JoinColumn(name = "commented_dynamic_id")
    Dynamic commentedDynamic;

    @ManyToOne
    @JoinColumn(name = "commented_column_id")
    Column commentedColumn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCommentSendDate() {
        return commentSendDate;
    }

    public void setCommentSendDate(Date commentSendDate) {
        this.commentSendDate = commentSendDate;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public ReferenceContentType getCommentedType() {
        return commentedType;
    }

    public void setCommentedType(ReferenceContentType commentedType) {
        this.commentedType = commentedType;
    }

    public User getCommentSender() {
        return commentSender;
    }

    public void setCommentSender(User commentSender) {
        this.commentSender = commentSender;
    }

    public Bangumi getCommentedBangumi() {
        return commentedBangumi;
    }

    public void setCommentedBangumi(Bangumi commentedBangumi) {
        this.commentedBangumi = commentedBangumi;
    }

    public Video getCommentedVideo() {
        return commentedVideo;
    }

    public void setCommentedVideo(Video commentedVideo) {
        this.commentedVideo = commentedVideo;
    }

    public Dynamic getCommentedDynamic() {
        return commentedDynamic;
    }

    public void setCommentedDynamic(Dynamic commentedDynamic) {
        this.commentedDynamic = commentedDynamic;
    }

    public Column getCommentedColumn() {
        return commentedColumn;
    }

    public void setCommentedColumn(Column commentedColumn) {
        this.commentedColumn = commentedColumn;
    }
}

