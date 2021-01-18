package com.db2020prj.controllers;

import com.db2020prj.controllers.request.TokenInfo;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.entities.Followship;
import com.db2020prj.entities.User;
import com.db2020prj.jwt.NeedToken;
import com.db2020prj.service.FollowshipService;
import com.db2020prj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FollowshipController {
    @Autowired
    UserService userService;
    @Autowired
    FollowshipService followshipService;

    @CrossOrigin
    @PostMapping(value = "/api/followship/follow/userId={id}")
    @NeedToken
    @ResponseBody
    public Result follow(@RequestBody TokenInfo token, @PathVariable("id") Integer id) {
        User followed = userService.getByUserId(id);
        if (null == followed) {
            return new Result(404, "target user not exists.");
        }
        User follower = userService.getByUserId(token.getUserId());
        Followship followship = followshipService.getFollowship(follower, followed);
        if (null != followship) {
            return new Result(300, "you have already followed this user");
        }
        followshipService.follow(follower, followed);
        return new Result(200, "followed success.");
    }

    @CrossOrigin
    @PostMapping(value = "/api/followship/unfollow/userId={id}")
    @NeedToken
    @ResponseBody
    public Result unFollow(@RequestBody TokenInfo token, @PathVariable("id") Integer id) {
        User followed = userService.getByUserId(id);
        if (null == followed) {
            return new Result(404, "target user not exists.");
        }
        User follower = userService.getByUserId(token.getUserId());
        Followship followship = followshipService.getFollowship(follower, followed);
        if (null == followship) {
            return new Result(300, "you have not followed this user yet");
        }
        followshipService.unfollow(followship);
        return new Result(200, "unfollowed success.");
    }
}
