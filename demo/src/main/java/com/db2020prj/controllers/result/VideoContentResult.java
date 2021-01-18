package com.db2020prj.controllers.result;

import com.db2020prj.entities.Comment;
import com.db2020prj.entities.Tag;
import com.db2020prj.entities.Video;
import com.db2020prj.service.CommentService;
import com.db2020prj.service.TagRelationService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class VideoContentResult extends VideoResult {
    @JsonProperty
    List<CommentResult> comments;
    @JsonProperty
    List<TagResult> tags;
    @JsonProperty
    boolean likedByCurrentWatcher;

    public VideoContentResult(Video video, boolean liked, TagRelationService tagRelationService, CommentService commentService) {
        super(video);
        this.likedByCurrentWatcher = liked;

        tags = new ArrayList<TagResult>();
        List<Tag> tmpTags = tagRelationService.findAllByTaggedContent(video);
        for (Tag tag: tmpTags) tags.add(new TagResult(tag));

        comments = new ArrayList<CommentResult>();
        List<Comment> tmpComments = commentService.findAllByCommentedContent(video);
        for (Comment comment: tmpComments) comments.add(new CommentResult(comment));
    }
}
