package com.db2020prj.controllers.request;

import java.util.List;

public class PostColumnRequest extends TokenInfo {
    String columnTitle;
    String columnText;
    String columnPicUrl;

    public String getColumnPicUrl() {
        return columnPicUrl;
    }

    public void setColumnPicUrl(String columnPicUrl) {
        this.columnPicUrl = columnPicUrl;
    }

    List<String> newColumnTags;

    public String getColumnTitle() {
        return columnTitle;
    }

    public void setColumnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public String getColumnText() {
        return columnText;
    }

    public void setColumnText(String columnText) {
        this.columnText = columnText;
    }

    public List<String> getNewColumnTags() {
        return newColumnTags;
    }

    public void setNewColumnTags(List<String> newColumnTags) {
        this.newColumnTags = newColumnTags;
    }
}
