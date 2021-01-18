package com.db2020prj.controllers.result;

import com.db2020prj.entities.Message;
import com.db2020prj.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserConversationResult {
    @JsonProperty
    String withWhomNickname;
    @JsonProperty
    int withWhomId;
    @JsonProperty
    UserMessageResult latestMessage;

    public UserConversationResult(User pov, User withWhom, Message message) {
        this.withWhomNickname = withWhom.getNickname();
        this.withWhomId = withWhom.getId();
        this.latestMessage = new UserMessageResult(message, pov);
    }
}
