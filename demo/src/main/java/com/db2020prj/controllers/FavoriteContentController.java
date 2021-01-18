package com.db2020prj.controllers;

import com.db2020prj.controllers.request.FavoriteRequest;
import com.db2020prj.controllers.result.FavoriteContentResult;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.entities.*;
import com.db2020prj.jwt.NeedToken;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoriteContentController {
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    FavoriteContentService favoriteContentService;
    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;
    @Autowired
    BangumiService bangumiService;
    @Autowired
    CommentService commentService;
    @Autowired
    ColumnService columnService;
    @Autowired
    DynamicService dynamicService;

    @CrossOrigin
    @GetMapping(value = "api/favoriteId={id}/content")
    @PassToken
    @ResponseBody
    public Result<FavoriteContentResult> getFavoriteContent(@PathVariable("id") int id) {
        Favorite favorite = favoriteService.findFavoriteById(id);
        if (null == favorite)
            return new Result(404, "Favorite Not Found!");
        List<FavoriteContent> contents = favoriteContentService.getFavoriteContentByFavorite(favorite);
        User owner = favorite.getFavoriteOwner();
        return new Result(new FavoriteContentResult(owner, favorite, contents), 200, "contnets found.");
    }

    @CrossOrigin
    @PostMapping(value = "api/favorite/video")
    @NeedToken
    @ResponseBody
    public Result favoriteVideo(@RequestBody FavoriteRequest request) {
        Video video = videoService.getVideoById(request.getFavoriteId());
        if (null == video) {
            return new Result(404, "target video not exists");
        }
        String favoriteName = request.getFavoriteName();
        if (favoriteName == "") favoriteName = "Default";
        User user = userService.getByUserId(request.getUserId());
        Favorite favorite = favoriteService.getOrCreateFavorite(favoriteName, user);
        if (favoriteContentService.alreadyFavored(favorite, video))
            return new Result(300, "already favored");
        favoriteContentService.favoriteContent(favorite, video);
        return new Result(200, "favorite video success.");
    }
    @CrossOrigin
    @PostMapping(value = "api/favorite/bangumi")
    @NeedToken
    @ResponseBody
    public Result favoriteBangumi(@RequestBody FavoriteRequest request) {
        Bangumi bangumi = bangumiService.getBangumiById(request.getFavoriteId());
        if (null == bangumi) {
            return new Result(404, "target bangumi not exists");
        }
        String favoriteName = request.getFavoriteName();
        if (favoriteName == "") favoriteName = "Default";
        User user = userService.getByUserId(request.getUserId());
        Favorite favorite = favoriteService.getOrCreateFavorite(favoriteName, user);
        if (favoriteContentService.alreadyFavored(favorite, bangumi))
            return new Result(300, "already favored");
        favoriteContentService.favoriteContent(favorite, bangumi);
        return new Result(200, "favorite bangumi success.");
    }
    @CrossOrigin
    @PostMapping(value = "api/favorite/column")
    @NeedToken
    @ResponseBody
    public Result favoriteColumn(@RequestBody FavoriteRequest request) {
        Column column = columnService.getColumnById(request.getFavoriteId());
        if (null == column) {
            return new Result(404, "target column not exists");
        }
        String favoriteName = request.getFavoriteName();
        if (favoriteName == "") favoriteName = "Default";
        User user = userService.getByUserId(request.getUserId());
        Favorite favorite = favoriteService.getOrCreateFavorite(favoriteName, user);
        if (favoriteContentService.alreadyFavored(favorite, column))
            return new Result(300, "already favored");
        favoriteContentService.favoriteContent(favorite, column);
        return new Result(200, "favorite column success.");
    }
}
