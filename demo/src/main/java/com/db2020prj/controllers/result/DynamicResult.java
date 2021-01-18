package com.db2020prj.controllers.result;

import com.db2020prj.entities.Dynamic;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DynamicResult {
    @JsonProperty
    Date sendTime;
    @JsonProperty
    String userNickname;
    @JsonProperty
    Integer userId;
    @JsonProperty
    String dynamicContent;
    @JsonProperty
    Integer numOfLikes;

    public DynamicResult(Dynamic dynamic) {
        this.sendTime = dynamic.getDynamicUploadDate();
        this.userNickname = dynamic.getDynamicUploader().getNickname();
        this.userId = dynamic.getDynamicUploader().getId();
        this.dynamicContent = dynamic.getDynamicContent();
        this.numOfLikes = dynamic.getDynamicLikeNumbers();
    }
}
