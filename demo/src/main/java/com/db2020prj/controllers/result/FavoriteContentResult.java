package com.db2020prj.controllers.result;

import com.db2020prj.entities.Favorite;
import com.db2020prj.entities.FavoriteContent;
import com.db2020prj.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class FavoriteContentResult {
    @JsonProperty
    String favoriteName;
    @JsonProperty
    String userNickname;
    @JsonProperty
    int favoriteId;
    @JsonProperty
    int userId;
    @JsonProperty
    List<BangumiResult> favoriteBangumis;
    @JsonProperty
    List<VideoResult> favoriteVideos;
    @JsonProperty
    List<ColumnResult> favoriteColumns;

    public FavoriteContentResult(User user, Favorite favorite, List<FavoriteContent> contents) {
        this.favoriteName = favorite.getFavoriteName();
        this.userNickname = user.getNickname();
        this.favoriteId = favorite.getId();
        this.userId = user.getId();
        this.favoriteBangumis = new ArrayList<>();
        this.favoriteVideos = new ArrayList<>();
        this.favoriteColumns = new ArrayList<>();
        for (FavoriteContent content: contents) {
            switch (content.getFcType()) {
                case VIDEO:
                    favoriteVideos.add(new VideoResult(content.getFcVideo()));
                    break;
                case COLUMN:
                    favoriteColumns.add(new ColumnResult(content.getFcColumn()));
                    break;
                case BANGUMI:
                    favoriteBangumis.add(new BangumiResult(content.getFcBangumi()));
                    break;
            }
        }
    }
}
