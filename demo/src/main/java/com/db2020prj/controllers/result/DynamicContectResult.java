package com.db2020prj.controllers.result;

import com.db2020prj.entities.Comment;
import com.db2020prj.entities.Dynamic;
import com.db2020prj.entities.Tag;
import com.db2020prj.service.CommentService;
import com.db2020prj.service.TagRelationService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.metrics.StartupStep;

import java.util.ArrayList;
import java.util.List;

public class DynamicContectResult extends DynamicResult {
    @JsonProperty
    List<CommentResult> comments;
    @JsonProperty
    List<TagResult> tags;
    @JsonProperty
    boolean likedByCurrentWatcher;

    public DynamicContectResult(Dynamic dynamic, boolean liked, TagRelationService tagRelationService, CommentService commentService) {
        super(dynamic);
        this.likedByCurrentWatcher = liked;

        comments = new ArrayList<>();
        List<Comment> tmpComment = commentService.findAllByCommentedContent(dynamic);
        for (Comment comment: tmpComment) comments.add(new CommentResult(comment));

        tags = new ArrayList<>();
        List<Tag> tmpTags = tagRelationService.findAllByTaggedContent(dynamic);
        for (Tag tag: tmpTags) tags.add(new TagResult(tag));

    }
}
