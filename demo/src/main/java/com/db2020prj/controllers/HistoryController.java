package com.db2020prj.controllers;

import com.db2020prj.controllers.result.HistoryResult;
import com.db2020prj.entities.User;
import com.db2020prj.jwt.NeedToken;
import com.db2020prj.service.HistoryService;
import com.db2020prj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HistoryController {
    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;

    @CrossOrigin
    @GetMapping(value = "/api/history")
    @NeedToken
    @ResponseBody
    public HistoryResult getHistories(@RequestParam Integer userId) {
        User user = userService.getByUserId(userId);
        return new HistoryResult(historyService.getUserHistory(user));
    }

}
