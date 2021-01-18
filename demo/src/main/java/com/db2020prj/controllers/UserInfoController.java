package com.db2020prj.controllers;

import com.db2020prj.controllers.result.Result;
import com.db2020prj.controllers.result.UserConversationResult;
import com.db2020prj.controllers.result.UserPublicInfoResult;
import com.db2020prj.entities.Followship;
import com.db2020prj.entities.User;
import com.db2020prj.jwt.OptionalToken;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.service.FollowshipService;
import com.db2020prj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInfoController {
    @Autowired
    UserService userService;
    @Autowired
    FollowshipService followshipService;

    @CrossOrigin
    @GetMapping(value = "api/userId={id}/info")
//    @PassToken
    @OptionalToken
    @ResponseBody
    public Result<UserPublicInfoResult> getUserInfo(@PathVariable("id") Integer id, @RequestParam(required = false) Integer povId) {
        User user = userService.getByUserId(id);
        if (null == user)
            return new Result(404, "User not found");

        int numOfFollowers = followshipService.getNumOfFollowers(user);
        int numOfFollowings = followshipService.getNumOfFollowings(user);
        boolean isHimself = false;
        boolean followedByCurrnetUser = false;
        if (povId != null) {
            User pov = userService.getByUserId(povId);
            if (povId != id) {
                Followship followship = followshipService.getFollowship(pov, user);
                followedByCurrnetUser = (followship != null);
            } else {
                isHimself = true;
            }
        }
        return new Result(new UserPublicInfoResult(user, numOfFollowers, numOfFollowings, followedByCurrnetUser, isHimself), 200, "get nickname success.");
    }
}
