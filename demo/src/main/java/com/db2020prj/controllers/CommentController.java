package com.db2020prj.controllers;

import com.db2020prj.controllers.request.PostCommentRequest;
import com.db2020prj.controllers.result.CommentResult;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.entities.*;
import com.db2020prj.jwt.AsAdmin;
import com.db2020prj.jwt.NeedToken;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;
    @Autowired
    ColumnService columnService;
    @Autowired
    BangumiService bangumiService;
    @Autowired
    DynamicService dynamicService;
    

    @CrossOrigin
    @NeedToken
    @PostMapping(value = "/api/comments/post/video")
    @ResponseBody
    public Result postCommentToVideo(@RequestBody PostCommentRequest request) {
        String content = request.getContent();
        if (null == content || content.length() == 0)
            return new Result(300, "comment content can't be blank!");
        Video video = videoService.getVideoById(request.getTargetId());
        if (null == video)
            return new Result(404, "Target video not exsits!");
        commentService.postComment(userService.getByUserId(request.getUserId()), content, video);
        return new Result(200, "Comment posted successfully");
    }

    @CrossOrigin
    @NeedToken
    @PostMapping(value = "/api/comments/post/dynamic")
    @ResponseBody
    public Result postCommentToDynamic(@RequestBody PostCommentRequest request) {
        String content = request.getContent();
        if (null == content || content.length() == 0)
            return new Result(300, "comment content can't be blank!");
        Dynamic dynamic = dynamicService.getDynamicById(request.getTargetId());
        if (null == dynamic)
            return new Result(404, "Target dynamic not exsits!");
        commentService.postComment(userService.getByUserId(request.getUserId()), content, dynamic);
        return new Result(200, "Comment posted successfully");
    }

    @CrossOrigin
    @NeedToken
    @PostMapping(value = "/api/comments/post/column")
    @ResponseBody
    public Result postCommentToColumn(@RequestBody PostCommentRequest request) {
        String content = request.getContent();
        if (null == content || content.length() == 0)
            return new Result(300, "comment content can't be blank!");
        Column column = columnService.getColumnById(request.getTargetId());
        if (null == column)
            return new Result(404, "Target column not exsits!");
        commentService.postComment(userService.getByUserId(request.getUserId()), content, column);
        return new Result(200, "Comment posted successfully");
    }

    @CrossOrigin
    @NeedToken
    @PostMapping(value = "/api/comments/post/bangumi")
    @ResponseBody
    public Result postCommentToBangumi(@RequestBody PostCommentRequest request) {
        String content = request.getContent();
        if (null == content || content.length() == 0)
            return new Result(300, "comment content can't be blank!");
        Bangumi bangumi = bangumiService.getBangumiById(request.getTargetId());
        if (null == bangumi)
            return new Result(404, "Target bangumi not exsits!");
        commentService.postComment(userService.getByUserId(request.getUserId()), content, bangumi);
        return new Result(200, "Comment posted successfully");
    }

    @CrossOrigin
    @PassToken
    @GetMapping(value = "/api/comments/get/videoId={id}")
    @ResponseBody
    public List<CommentResult> getVideoComments(@PathVariable("id") Integer id) {
        Video video = videoService.getVideoById(id);
        if (null == video)
            return null;
        List<Comment> comments = commentService.findAllByCommentedContent(video);
        List<CommentResult> results = new ArrayList<>();
        for (Comment comment: comments)
            results.add(new CommentResult(comment));
        return results;
    }

    @CrossOrigin
    @PassToken
    @GetMapping(value = "/api/comments/get/bangumiId={id}")
    @ResponseBody
    public List<CommentResult> getBangumiComments(@PathVariable("id") Integer id) {
        Bangumi bangumi = bangumiService.getBangumiById(id);
        if (null == bangumi)
            return null;
        List<Comment> comments = commentService.findAllByCommentedContent(bangumi);
        List<CommentResult> results = new ArrayList<>();
        for (Comment comment: comments)
            results.add(new CommentResult(comment));
        return results;
    }

    @CrossOrigin
    @PassToken
    @GetMapping(value = "/api/comments/get/columnId={id}")
    @ResponseBody
    public List<CommentResult> getColumnComments(@PathVariable("id") Integer id) {
        Column column = columnService.getColumnById(id);
        if (null == column)
            return null;
        List<Comment> comments = commentService.findAllByCommentedContent(column);
        List<CommentResult> results = new ArrayList<>();
        for (Comment comment: comments)
            results.add(new CommentResult(comment));
        return results;
    }

    @CrossOrigin
    @PassToken
    @GetMapping(value = "/api/comments/get/dynamicId={id}")
    @ResponseBody
    public List<CommentResult> getDynamicComments(@PathVariable("id") Integer id) {
        Dynamic dynamic = dynamicService.getDynamicById(id);
        if (null == dynamic)
            return null;
        List<Comment> comments = commentService.findAllByCommentedContent(dynamic);
        List<CommentResult> results = new ArrayList<>();
        for (Comment comment: comments)
            results.add(new CommentResult(comment));
        return results;
    }
}
