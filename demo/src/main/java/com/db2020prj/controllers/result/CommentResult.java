package com.db2020prj.controllers.result;

import com.db2020prj.entities.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CommentResult {
    @JsonProperty
    String commentSenderNickname;
    @JsonProperty
    Integer commentSenderId;
    @JsonProperty
    String commentContent;
    @JsonProperty
    Date commentSendDate;
    @JsonProperty
    String userNickname;
    @JsonProperty
    Integer numOfLikes;

    public CommentResult(Comment comment) {
        this.commentContent = comment.getCommentContent();
        this.commentSenderId = comment.getCommentSender().getId();
        this.commentSenderNickname = comment.getCommentSender().getNickname();
        this.commentSendDate = comment.getCommentSendDate();
        this.numOfLikes = comment.getCommentLikeNumbers();
        this.userNickname = comment.getCommentSender().getNickname();
    }

    public String getCommentSenderNickname() {
        return commentSenderNickname;
    }

    public void setCommentSenderNickname(String commentSenderNickname) {
        this.commentSenderNickname = commentSenderNickname;
    }

    public Integer getCommentSenderId() {
        return commentSenderId;
    }

    public void setCommentSenderId(Integer commentSenderId) {
        this.commentSenderId = commentSenderId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentSendDate() {
        return commentSendDate;
    }

    public void setCommentSendDate(Date commentSendDate) {
        this.commentSendDate = commentSendDate;
    }
}
