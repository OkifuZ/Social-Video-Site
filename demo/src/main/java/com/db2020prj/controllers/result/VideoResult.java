package com.db2020prj.controllers.result;

import com.db2020prj.entities.Video;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

public class VideoResult {
    @JsonProperty
    int videoId;
    @JsonProperty
    String videoName;
    @JsonProperty
    String videoDescription;
    @JsonProperty
    String videoContentUrl;
    @JsonProperty
    Integer userId;
    @JsonProperty
    String username;
    @JsonProperty
    String nickname;
    @JsonProperty
    String videoImageUrl;
    @JsonProperty
    Date videoUploadDate;
    @JsonProperty
    Integer numOfLikes;

    public VideoResult(Video video) {
        this.videoId = video.getId();
        this.videoName = video.getVideoName();
        this.videoDescription = video.getVideoDescription();
        this.userId = video.getVideoUploader().getId();
        this.username = video.getVideoUploader().getUsername();
        this.nickname = video.getVideoUploader().getNickname();
        this.videoImageUrl = video.getVideoImage().getImageUrl();
        this.videoContentUrl = video.getVideoContentUrl();
        this.videoUploadDate = video.getVideoUploadDate();
        this.numOfLikes = video.getVideoLikeNumbers();
    }

    public Date getVideoUploadDate() {
        return videoUploadDate;
    }

    public void setVideoUploadDate(Date videoUploadDate) {
        this.videoUploadDate = videoUploadDate;
    }

    public String getVideoImageUrl() {
        return videoImageUrl;
    }

    public void setVideoImageUrl(String videoImageUrl) {
        this.videoImageUrl = videoImageUrl;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getVideoContentUrl() {
        return videoContentUrl;
    }

    public void setVideoContentUrl(String videoContentUrl) {
        this.videoContentUrl = videoContentUrl;
    }
}
