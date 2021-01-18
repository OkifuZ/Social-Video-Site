package com.db2020prj.controllers;

import com.db2020prj.controllers.request.RegisterRequestUser;
import com.db2020prj.entities.User;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/register")
    @PassToken
    @ResponseBody
    public Result register(@RequestBody RegisterRequestUser requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(requestUser.getPassword());
        newUser.setNickname(requestUser.getNickname());

        if (username.length() < 1)
            return new Result(400, "Invalid username!");
        if (requestUser.getPassword().length() < 8)
            return new Result(400, "Password must be longer than 8 characters");
        String result = userService.add(newUser);
        if (result.equals("SUCCESS"))
            return new Result(200, "Register for user [" + username + "] succeeded");
        return new Result(400, "Username already Exists!");
    }
}

