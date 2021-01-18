package com.db2020prj.controllers.result;

import com.db2020prj.entities.Tag;
import com.db2020prj.entities.TagRelation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TagRelatedContentResult extends TagResult{
    @JsonProperty
    List<BangumiResult> tagRelatedBangumis;
    @JsonProperty
    List<VideoResult> tagRelatedVideos;
    @JsonProperty
    List<ColumnResult> tagRelatedColumns;
    @JsonProperty
    List<DynamicResult> tagRelatedDynamics;

    public TagRelatedContentResult(Tag tag, List<TagRelation> tagRelations) {
        super(tag);
        tagRelatedDynamics = new ArrayList<>();
        tagRelatedVideos = new ArrayList<>();
        tagRelatedColumns = new ArrayList<>();
        tagRelatedBangumis = new ArrayList<>();
        for (TagRelation tagRelation: tagRelations) {
            switch (tagRelation.getTaggedType()) {
                case BANGUMI:
                    tagRelatedBangumis.add(new BangumiResult(tagRelation.getTaggedBangumi()));
                    break;
                case VIDEO:
                    tagRelatedVideos.add(new VideoResult(tagRelation.getTaggedVideo()));
                    break;
                case COLUMN:
                    tagRelatedColumns.add(new ColumnResult(tagRelation.getTaggedColumn()));
                    break;
                case DYNAMIC:
                    tagRelatedDynamics.add(new DynamicResult(tagRelation.getTaggedDynamic()));
                    break;
            }
        }
    }


}
