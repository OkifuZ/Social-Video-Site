package com.db2020prj.controllers;

import com.db2020prj.controllers.request.PostDynamicRequest;
import com.db2020prj.controllers.result.DynamicContectResult;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.entities.Dynamic;
import com.db2020prj.entities.TagRelation;
import com.db2020prj.entities.User;
import com.db2020prj.jwt.NeedToken;
import com.db2020prj.jwt.OptionalToken;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DynamicController {
    @Autowired
    DynamicService dynamicService;
    @Autowired
    UserService userService;
    @Autowired
    TagRelationService tagRelationService;
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;

    @CrossOrigin
    @PostMapping(value = "api/dynamics/post")
    @NeedToken
    @ResponseBody
    public Result postDynamic(@RequestBody PostDynamicRequest request) {
        String text = request.getMomentText();
        if (null == text || text.length() == 0)
            return new Result(300, "Content cannot be blank!");
        User uploader = userService.getByUserId(request.getUserId());
        dynamicService.addDynamic(request, uploader);
        return new Result(200, "Dynamic posted successfully.");
    }

    @CrossOrigin
    @GetMapping(value = "api/dynamics/id={id}")
//    @PassToken
    @OptionalToken
    @ResponseBody
    public Result<DynamicContectResult> getDynamicById(@PathVariable("id") Integer id, @RequestParam(required = false) Integer userId) {
        Dynamic dynamic = dynamicService.getDynamicById(id);
        if (null == dynamic)
            return new Result(404, "Resource not found");
        boolean liked = false;
        if (userId != null) {
            User watcher = userService.getByUserId(userId);
            liked = (null != likeService.getLikeByUserAndContent(watcher, dynamic));
        }
        return new Result(new DynamicContectResult(dynamic, liked, tagRelationService, commentService), 200, "Dynamic found");
    }
}
