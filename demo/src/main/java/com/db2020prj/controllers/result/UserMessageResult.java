package com.db2020prj.controllers.result;

import com.db2020prj.entities.Message;
import com.db2020prj.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserMessageResult {
    @JsonProperty
    int fromNum;
    @JsonProperty
    String message;
    @JsonProperty
    Date sendTime;
    @JsonProperty
    String receiverNickname;
    @JsonProperty
    String senderNicknname;

    public UserMessageResult(Message message, User pov) {
        this.message = message.getMessageContent();
        this.sendTime = message.getMessageSendDate();
        this.fromNum = (pov.getId() == message.getMessageSender().getId()) ? 1 : 2;
        this.senderNicknname = message.getMessageSender().getNickname();
        this.receiverNickname = message.getMessageReceiver().getNickname();
    }
}
