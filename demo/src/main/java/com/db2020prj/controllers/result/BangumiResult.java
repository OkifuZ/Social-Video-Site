package com.db2020prj.controllers.result;

import com.db2020prj.entities.Bangumi;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BangumiResult {
    @JsonProperty
    int bangumiId;
    @JsonProperty
    String bangumiName;
    @JsonProperty
    Date bangumiUploadDate;
    @JsonProperty
    Integer bangumiPlayTimes;
    @JsonProperty
    String bangumiContentUrl;
    @JsonProperty
    String bangumiCategory;
    @JsonProperty
    String bangumiDescription;
    @JsonProperty
    String bangumiCoverUrl;
    @JsonProperty
    Integer numOfLikes;

    public BangumiResult(Bangumi bangumi) {
        this.bangumiId = bangumi.getId();
        this.bangumiCategory = bangumi.getBangumiCategory();
        this.bangumiName = bangumi.getBangumiName();
        this.bangumiUploadDate = bangumi.getBangumiUploadDate();
        this.bangumiPlayTimes = bangumi.getBangumiPlayTimes();
        this.bangumiContentUrl = bangumi.getBangumiContentUrl();
        this.bangumiDescription = bangumi.getBangumiDescription();
        this.bangumiCoverUrl = bangumi.getBangumiImage().getImageUrl();
        this.numOfLikes = bangumi.getBangumiLikeNumbers();
    }
}
