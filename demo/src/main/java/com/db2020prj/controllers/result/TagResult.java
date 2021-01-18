package com.db2020prj.controllers.result;

import com.db2020prj.entities.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TagResult {
    @JsonProperty
    Integer id;
    @JsonProperty
    String tagName;

    public TagResult(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getTagName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
