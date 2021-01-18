package com.db2020prj.controllers.result;

import com.db2020prj.entities.Bangumi;
import com.db2020prj.entities.Comment;
import com.db2020prj.entities.Tag;
import com.db2020prj.service.CommentService;
import com.db2020prj.service.TagRelationService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.metrics.StartupStep;

import java.util.ArrayList;
import java.util.List;

public class BangumiContentResult extends BangumiResult {
    @JsonProperty
    List<CommentResult> comments;
    @JsonProperty
    List<TagResult> tags;
    @JsonProperty
    boolean likedByCurrentWatcher;

    public BangumiContentResult(Bangumi bangumi, boolean liked, TagRelationService tagRelationService, CommentService commentService) {
        super(bangumi);
        this.likedByCurrentWatcher = liked;

        comments = new ArrayList<>();
        List<Comment> tmpComments = commentService.findAllByCommentedContent(bangumi);
        for (Comment comment: tmpComments) comments.add(new CommentResult(comment));

        tags = new ArrayList<>();
        List<Tag> tmpTags = tagRelationService.findAllByTaggedContent(bangumi);
        for (Tag tag: tmpTags) tags.add(new TagResult(tag));
    }
}
