package com.db2020prj.controllers.result;

import com.db2020prj.entities.Bangumi;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class BangumisByCateResult {
    @JsonProperty
    String cateName;
    @JsonProperty
    List<BangumiResult> cate;

    public BangumisByCateResult() {
    }

    public BangumisByCateResult(String cateName, List<Bangumi> cate) {
        this.cateName = cateName;
        this.cate = new ArrayList<>();
        for (Bangumi bangumi: cate) {
            this.cate.add(new BangumiResult(bangumi));
        }
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateName() {
        return cateName;
    }

    public List<BangumiResult> getCate() {
        return cate;
    }

    public void setCate(List<BangumiResult> cate) {
        this.cate = cate;
    }
}