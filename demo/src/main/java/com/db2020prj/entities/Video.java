package com.db2020prj.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "video")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "video_id")
    int id;

    @CreatedDate
    Date videoUploadDate;

    String videoDuration;
    String videoName;
    Integer videoPlayTimes;
    String videoContentUrl;
    String videoDescription;
    Integer videoLikeNumbers;

    public void setVideoPlayTimes(Integer videoPlayTimes) {
        this.videoPlayTimes = videoPlayTimes;
    }

    public Integer getVideoLikeNumbers() {
        return videoLikeNumbers;
    }

    public void setVideoLikeNumbers(Integer videoLikeNumbers) {
        this.videoLikeNumbers = videoLikeNumbers;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    @ManyToOne
    @JoinColumn(name = "video_image_id")
    Image videoImage;

    @ManyToOne
    @JoinColumn(name = "video_uploader_id")
    User videoUploader;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getVideoUploadDate() {
        return videoUploadDate;
    }

    public void setVideoUploadDate(Date videoUploadDate) {
        this.videoUploadDate = videoUploadDate;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public int getVideoPlayTimes() {
        return videoPlayTimes;
    }

    public void setVideoPlayTimes(int videoPlayTimes) {
        this.videoPlayTimes = videoPlayTimes;
    }

    public String getVideoContentUrl() {
        return videoContentUrl;
    }

    public void setVideoContentUrl(String videoContentUrl) {
        this.videoContentUrl = videoContentUrl;
    }

    public Image getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(Image videoImage) {
        this.videoImage = videoImage;
    }

    public User getVideoUploader() {
        return videoUploader;
    }

    public void setVideoUploader(User videoUploader) {
        this.videoUploader = videoUploader;
    }
}
