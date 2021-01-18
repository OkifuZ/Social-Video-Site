package com.db2020prj.controllers.result;

import com.db2020prj.entities.Column;
import com.db2020prj.reources.Icon;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ColumnResult {
    @JsonProperty
    int columnId;
    @JsonProperty
    String columnName;
    @JsonProperty
    String columnAbstract;
    @JsonProperty
    String icon;
    @JsonProperty
    String columnImageUrl;
    @JsonProperty
    String username;
    @JsonProperty
    String nickname;
    @JsonProperty
    int userId;
    @JsonProperty
    Date columnUploadDate;
    @JsonProperty
    Integer numOfLikes;

    public ColumnResult(Column column) {
        this.columnId = column.getId();
        this.columnName = column.getColumnName();
        String content = column.getColumnContent();
        this.columnAbstract = content.substring(0, Math.min(100, content.length()));
        this.icon = Icon.random_icon();
        this.username = column.getColumnUploader().getUsername();
        this.nickname = column.getColumnUploader().getNickname();
        this.userId = column.getColumnUploader().getId();
        this.columnUploadDate = column.getColumnUploadDate();
        this.columnImageUrl = column.getColumnImage().getImageUrl();
        this.numOfLikes = column.getColumnLikeNumbers();
    }
}
