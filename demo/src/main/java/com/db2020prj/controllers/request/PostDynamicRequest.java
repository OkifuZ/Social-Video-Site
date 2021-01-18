package com.db2020prj.controllers.request;

import java.util.List;

public class PostDynamicRequest extends TokenInfo {
    String momentText;
    List<String> newMomentTags;

    public String getMomentText() {
        return momentText;
    }

    public void setMomentText(String momentText) {
        this.momentText = momentText;
    }

    public List<String> getNewMomentTags() {
        return newMomentTags;
    }

    public void setNewMomentTags(List<String> newMomentTags) {
        this.newMomentTags = newMomentTags;
    }
}
