package com.db2020prj.controllers.result;

import com.db2020prj.entities.Column;
import com.db2020prj.entities.Comment;
import com.db2020prj.entities.Tag;
import com.db2020prj.service.CommentService;
import com.db2020prj.service.TagRelationService;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ColumnContentResult extends ColumnResult {
    @JsonProperty
    String columnContect;
    @JsonProperty
    List<CommentResult> comments;
    @JsonProperty
    List<TagResult> tags;
    @JsonProperty
    boolean likedByCurrentWatcher;

    public ColumnContentResult(Column column, boolean liked, TagRelationService tagRelationService, CommentService commentService) {
        super(column);
        this.likedByCurrentWatcher = liked;

        comments = new ArrayList<>();
        List<Comment> tmpComments = commentService.findAllByCommentedContent(column);
        for (Comment comment: tmpComments) comments.add(new CommentResult(comment));

        tags = new ArrayList<>();
        List<Tag> tmpTags = tagRelationService.findAllByTaggedContent(column);
        for (Tag tag: tmpTags) tags.add(new TagResult(tag));

    }
}
