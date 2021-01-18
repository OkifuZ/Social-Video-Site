package com.db2020prj.controllers.result;

import com.db2020prj.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPublicInfoResult {
    @JsonProperty
    String nickname;
    @JsonProperty
    Integer numOfFollowers;
    @JsonProperty
    Integer numOfFollowings;
    @JsonProperty
    boolean followedByCurrentUser;
    @JsonProperty
    boolean isHimself;

    public UserPublicInfoResult(User user, Integer followers, Integer followings, boolean followed, boolean himself) {
        this.nickname = user.getNickname();
        this.numOfFollowers = followers;
        this.numOfFollowings = followings;
        this.followedByCurrentUser = followed;
        this.isHimself = himself;
    }

    public Integer getNumOfFollowers() {
        return numOfFollowers;
    }

    public void setNumOfFollowers(Integer numOfFollowers) {
        this.numOfFollowers = numOfFollowers;
    }

    public Integer getNumOfFollowings() {
        return numOfFollowings;
    }

    public void setNumOfFollowings(Integer numOfFollowings) {
        this.numOfFollowings = numOfFollowings;
    }

    public boolean isFollowedByCurrentUser() {
        return followedByCurrentUser;
    }

    public void setFollowedByCurrentUser(boolean followedByCurrentUser) {
        this.followedByCurrentUser = followedByCurrentUser;
    }

    public boolean isHimself() {
        return isHimself;
    }

    public void setHimself(boolean himself) {
        isHimself = himself;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}