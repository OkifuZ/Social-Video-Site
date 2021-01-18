package com.db2020prj.controllers;

import com.db2020prj.controllers.request.TokenInfo;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.entities.*;
import com.db2020prj.jwt.NeedToken;
import com.db2020prj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {
    @Autowired
    VideoService videoService;
    @Autowired
    BangumiService bangumiService;
    @Autowired
    CommentService commentService;
    @Autowired
    ColumnService columnService;
    @Autowired
    DynamicService dynamicService;
    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    
    @CrossOrigin
    @PostMapping(value = "/api/like/videoId={id}")
    @NeedToken
    @ResponseBody
    public Result likeVideo(@RequestBody TokenInfo token, @PathVariable("id") Integer id) {
        Video video = videoService.getVideoById(id);
        if (null == video) {
            return new Result(404, "target video not exists.");
        }
        User user = userService.getByUserId(token.getUserId());
        if (null != likeService.getLikeByUserAndContent(user, video)) {
            return new Result(300, "you have already liked this video.");
        }
        likeService.likeContent(user, video);
        return new Result(200, "liked success");
    }

    @CrossOrigin
    @PostMapping(value = "/api/like/bangumiId={id}")
    @NeedToken
    @ResponseBody
    public Result likeBangumi(@RequestBody TokenInfo token, @PathVariable("id") Integer id) {
        Bangumi bangumi = bangumiService.getBangumiById(id);
        if (null == bangumi) {
            return new Result(404, "target bangumi not exists.");
        }
        User user = userService.getByUserId(token.getUserId());
        if (null != likeService.getLikeByUserAndContent(user, bangumi)) {
            return new Result(300, "you have already liked this bangumi.");
        }
        likeService.likeContent(user, bangumi);
        return new Result(200, "liked success");
    }

    @CrossOrigin
    @PostMapping(value = "/api/like/columnId={id}")
    @NeedToken
    @ResponseBody
    public Result likeColumn(@RequestBody TokenInfo token, @PathVariable("id") Integer id) {
        Column column = columnService.getColumnById(id);
        if (null == column) {
            return new Result(404, "target column not exists.");
        }
        User user = userService.getByUserId(token.getUserId());
        if (null != likeService.getLikeByUserAndContent(user, column)) {
            return new Result(300, "you have already liked this column.");
        }
        likeService.likeContent(user, column);
        return new Result(200, "liked success");
    }

    @CrossOrigin
    @PostMapping(value = "/api/like/dynamicId={id}")
    @NeedToken
    @ResponseBody
    public Result likeDynamic(@RequestBody TokenInfo token, @PathVariable("id") Integer id) {
        Dynamic dynamic = dynamicService.getDynamicById(id);
        if (null == dynamic) {
            return new Result(404, "target dynamic not exists.");
        }
        User user = userService.getByUserId(token.getUserId());
        if (null != likeService.getLikeByUserAndContent(user, dynamic)) {
            return new Result(300, "you have already liked this dynamic.");
        }
        likeService.likeContent(user, dynamic);
        return new Result(200, "liked success");
    }

    @CrossOrigin
    @PostMapping(value = "/api/like/commentId={id}")
    @NeedToken
    @ResponseBody
    public Result likeComment(@RequestBody TokenInfo token, @PathVariable("id") Integer id) {
        Comment comment = commentService.getCommentById(id);
        if (null == comment) {
            return new Result(404, "target comment not exists.");
        }
        User user = userService.getByUserId(token.getUserId());
        if (null != likeService.getLikeByUserAndContent(user, comment)) {
            return new Result(300, "you have already liked this comment.");
        }
        likeService.likeContent(user, comment);
        return new Result(200, "liked success");
    }
}
