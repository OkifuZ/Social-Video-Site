package com.db2020prj.controllers.result;

import com.db2020prj.entities.Dynamic;
import com.db2020prj.entities.User;
import com.db2020prj.reources.Icon;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDynamicResult {
    @JsonProperty
    String dynamicContent;
    @JsonProperty
    String dynamicAbstract;
    @JsonProperty
    String icon;
    @JsonProperty
    Integer dynamicId;
    @JsonProperty
    Integer userId;
    @JsonProperty
    String userNickname;

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public String getDynamicAbstract() {
        return dynamicAbstract;
    }

    public void setDynamicAbstract(String dynamicAbstract) {
        this.dynamicAbstract = dynamicAbstract;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public UserDynamicResult(User user, Dynamic dynamic) {
        this.dynamicContent = dynamic.getDynamicContent();
        this.dynamicAbstract = dynamicContent.substring(
                0, Math.min(100, dynamicContent.length())
        );
        this.icon = Icon.random_icon();
        this.userNickname = user.getNickname();
        this.userId = user.getId();
        this.dynamicId = dynamic.getId();


    }
}
