package com.db2020prj.controllers;

import com.db2020prj.controllers.request.PostMessageRequest;
import com.db2020prj.controllers.request.TokenInfo;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.controllers.result.UserConversationResult;
import com.db2020prj.controllers.result.UserMessageResult;
import com.db2020prj.entities.Message;
import com.db2020prj.entities.User;
import com.db2020prj.jwt.NeedToken;
import com.db2020prj.jwt.Token;
import com.db2020prj.service.MessageService;
import com.db2020prj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    @CrossOrigin
    @NeedToken
    @GetMapping(value = "/api/messages/with={id}")
    @ResponseBody
    public List<UserMessageResult> getMessagesWith(@RequestParam Integer userId, @PathVariable("id") int id) {
        User pov = userService.getByUserId(userId);
        User with = userService.getByUserId(id);
        if (null == with)
            return null;
        List<Message> messages = messageService.findAllMessagesByRelatedUsers(pov, with);
        List<UserMessageResult> results = new ArrayList<>();
        for (Message message: messages)
            results.add(new UserMessageResult(message, pov));
        return results;
    }

    @CrossOrigin
    @NeedToken
    @PostMapping(value = "api/messages/send")
    @ResponseBody
    public Result sendMessageTo(@RequestBody PostMessageRequest request) {
        int fromId = request.getUserId();
        User from = userService.getByUserId(fromId);
        int targetId = request.getTargetUserId();
        User target = userService.getByUserId(targetId);
        if (null == target)
            return new Result(300, "Target user not exists!");
        if (fromId == targetId)
            return new Result(300, "Can't send message to oneself!");
        if (request.getMessages() == null || request.getMessages().length() == 0)
            return new Result(300, "Can't send blank message!");
        messageService.sendMessage(request.getMessages(), from, target);
        return new Result(200, "Message sent successfully.");

    }

    @CrossOrigin
    @NeedToken
    @GetMapping(value = "api/messages/all")
    @ResponseBody
    public List<UserConversationResult> getConversations(@RequestParam Integer userId) {
//        User pov = userService.getByUserId(token.getUserId());
        User pov = userService.getByUserId(userId);
        List<Message> messages = messageService.findAllMessagesByRelatedUser(pov);
        Map<User, Message> latestMessage = new HashMap<>();
        List<UserConversationResult> results = new ArrayList<>();
        for (Message message: messages) {
            if (message.getMessageSender().getId() != pov.getId())
                latestMessage.put(message.getMessageSender(), message);
            else latestMessage.put(message.getMessageReceiver(), message);
        }
        for (Map.Entry<User, Message> entry : latestMessage.entrySet()) {
            User with = entry.getKey();
            Message message = entry.getValue();
            results.add(new UserConversationResult(pov, with, message));
        }
        return results;
    }

}
