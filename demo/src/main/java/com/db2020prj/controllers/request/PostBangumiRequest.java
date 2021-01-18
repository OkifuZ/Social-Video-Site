package com.db2020prj.controllers.request;

import java.util.List;

public class PostBangumiRequest extends TokenInfo {
    String BangumiTitle;
    String BangumiUrl;
    String BangumiPicUrl;
    String BangumiDes;
    String BangumiCategory;
    List<String> newBangumiTags;

    public String getBangumiTitle() {
        return BangumiTitle;
    }

    public void setBangumiTitle(String bangumiTitle) {
        BangumiTitle = bangumiTitle;
    }

    public String getBangumiUrl() {
        return BangumiUrl;
    }

    public void setBangumiUrl(String bangumiUrl) {
        BangumiUrl = bangumiUrl;
    }

    public String getBangumiPicUrl() {
        return BangumiPicUrl;
    }

    public void setBangumiPicUrl(String bangumiPicUrl) {
        BangumiPicUrl = bangumiPicUrl;
    }

    public String getBangumiDes() {
        return BangumiDes;
    }

    public void setBangumiDes(String bangumiDes) {
        BangumiDes = bangumiDes;
    }

    public String getBangumiCategory() {
        return BangumiCategory;
    }

    public void setBangumiCategory(String bangumiCategory) {
        BangumiCategory = bangumiCategory;
    }

    public List<String> getNewBangumiTags() {
        return newBangumiTags;
    }

    public void setNewBangumiTags(List<String> newBangumiTags) {
        this.newBangumiTags = newBangumiTags;
    }
}
