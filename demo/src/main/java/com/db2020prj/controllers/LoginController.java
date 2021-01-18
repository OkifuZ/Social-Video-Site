package com.db2020prj.controllers;

import com.db2020prj.controllers.request.LoginRequestUser;
import com.db2020prj.controllers.result.LoginResult;
import com.db2020prj.entities.User;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.jwt.Token;
import com.db2020prj.service.AdminService;
import com.db2020prj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @PassToken
    @ResponseBody
    public Result<LoginResult> login(@RequestBody LoginRequestUser requestUser) {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        //System.out.println("username: " + username);
        //System.out.println("password: " + requestUser.getPassword() );

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400, "Wrong username or password");
        }
        boolean isAdmin = adminService.isAdmin(user);
        return new Result(new LoginResult(
                Token.getToken(user, isAdmin),
                user, isAdmin),
                200,
                "login success");
    }
}


