package com.db2020prj.controllers.request;

import java.util.List;

public class PostVideoRequest extends TokenInfo{
    String videoTitle;
    String videoUrl;
    String videoPicUrl;
    String videoDes;
    List<String> newVideoTags;

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoPicUrl() {
        return videoPicUrl;
    }

    public void setVideoPicUrl(String videoPicUrl) {
        this.videoPicUrl = videoPicUrl;
    }

    public String getVideoDes() {
        return videoDes;
    }

    public void setVideoDes(String videoDes) {
        this.videoDes = videoDes;
    }

    public List<String> getNewVideoTags() {
        return newVideoTags;
    }

    public void setNewVideoTags(List<String> newVideoTags) {
        this.newVideoTags = newVideoTags;
    }
}
