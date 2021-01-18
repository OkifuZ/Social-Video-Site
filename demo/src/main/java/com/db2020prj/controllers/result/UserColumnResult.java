package com.db2020prj.controllers.result;

import com.db2020prj.entities.Column;
import com.db2020prj.entities.User;
import com.db2020prj.reources.Icon;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserColumnResult {
    @JsonProperty
    String columnName;
    @JsonProperty
    String columnAbstract;
    @JsonProperty
    String icon;
    @JsonProperty
    String userNickname;
    @JsonProperty
    Integer columnId;
    @JsonProperty
    Integer userId;

    public UserColumnResult(User user, Column column) {
        this.columnName = column.getColumnName();
        this.columnAbstract = column.getColumnContent().substring(
                0, Math.min(100, column.getColumnContent().length())
        );
        this.icon = Icon.random_icon();
        this.userNickname = user.getNickname();
        this.columnId = column.getId();
        this.userId = user.getId();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnAbstract() {
        return columnAbstract;
    }

    public void setColumnAbstract(String columnAbstract) {
        this.columnAbstract = columnAbstract;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
