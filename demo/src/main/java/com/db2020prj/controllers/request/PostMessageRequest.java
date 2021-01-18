package com.db2020prj.controllers.request;

public class PostMessageRequest extends TokenInfo{
    int targetUserId;
    String messages;

    public int getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(int targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
