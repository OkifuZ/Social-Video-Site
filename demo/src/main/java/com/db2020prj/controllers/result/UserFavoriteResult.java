package com.db2020prj.controllers.result;

import com.db2020prj.entities.Favorite;
import com.db2020prj.entities.Image;
import com.db2020prj.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserFavoriteResult {
    @JsonProperty
    String FavoriteName;
    @JsonProperty
    Integer FavoriteId;
    @JsonProperty
    String FavoriteOwnerNickname;
    @JsonProperty
    Integer FavoriteOwnerId;
    @JsonProperty
    String coverUrl;

    public UserFavoriteResult(User owner, Favorite favorite, Image cover) {
        this.FavoriteId = favorite.getId();
        this.FavoriteName = favorite.getFavoriteName();
        this.FavoriteOwnerId = owner.getId();
        this.FavoriteOwnerNickname = owner.getNickname();
        this.coverUrl = (null == cover) ? "" : cover.getImageUrl();
    }
}
