package com.db2020prj.controllers.result;

import com.db2020prj.entities.User;
import com.db2020prj.jwt.Token;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResult {
    @JsonProperty
    String token;
    @JsonProperty
    String nickname;
    @JsonProperty
    boolean isAdmin;
    @JsonProperty
    Integer userId;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public LoginResult(String token, User user, boolean isAdmin) {
        this.token = token;
        this.nickname = user.getNickname();
        this.isAdmin = isAdmin;
        this.userId = user.getId();
    }

    public LoginResult(String token, User user) {
        this.token = token;
        this.nickname = user.getNickname();
        this.isAdmin = false;
        this.userId = user.getId();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
