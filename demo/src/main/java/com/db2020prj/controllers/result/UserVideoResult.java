package com.db2020prj.controllers.result;

import com.db2020prj.entities.User;
import com.db2020prj.entities.Video;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserVideoResult {
    @JsonProperty
    String videoName;
    @JsonProperty
    String videoImageUrl;
    @JsonProperty
    String videoDescription;
    @JsonProperty
    Integer videoId;
    @JsonProperty
    Integer userId;
    @JsonProperty
    String userNickname;

    public UserVideoResult(User user, Video video) {
        this.videoName = video.getVideoName();
        this.videoDescription = video.getVideoDescription();
        this.videoId = video.getId();
        this.videoDescription = video.getVideoDescription();
        this.userId = user.getId();
        this.userNickname = user.getNickname();
        this.videoImageUrl = video.getVideoImage().getImageUrl();
    }
}
