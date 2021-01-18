package com.db2020prj.controllers;

import com.db2020prj.controllers.result.*;
import com.db2020prj.entities.*;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserContentController {
    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;
    @Autowired
    ColumnService columnService;
    @Autowired
    BangumiService bangumiService;
    @Autowired
    DynamicService dynamicService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    FavoriteContentService favoriteContentService;

    @CrossOrigin
    @PassToken
    @GetMapping(value = "api/user_id={id}/dynamics")
    @ResponseBody
    public List<UserDynamicResult> getUserDynamics(@PathVariable("id") int id) {
        User user = userService.getByUserId(id);
        if (null == user)
            return null;
        List<Dynamic> dynamics = dynamicService.findAllByDynamicUploader(user);
        List<UserDynamicResult> results = new ArrayList<>();
        for (Dynamic dynamic: dynamics)
            results.add(new UserDynamicResult(user, dynamic));
        return results;
    }

    @CrossOrigin
    @PassToken
    @GetMapping(value = "api/user_id={id}/columns")
    @ResponseBody
    public List<UserColumnResult> getUserColumns(@PathVariable("id") int id) {
        User user = userService.getByUserId(id);
        if (null == user)
            return null;
        List<Column> columns = columnService.findAllByColumnUploader(user);
        List<UserColumnResult> results = new ArrayList<>();
        for (Column column: columns)
            results.add(new UserColumnResult(user, column));
        return results;
    }

    @CrossOrigin
    @PassToken
    @GetMapping(value = "api/user_id={id}/videos")
    @ResponseBody
    public List<UserVideoResult> getUserVideos(@PathVariable("id") int id) {
        User user = userService.getByUserId(id);
        if (null == user)
            return null;
        List<Video> videos = videoService.findAllByVideoUploader(user);
        List<UserVideoResult> results = new ArrayList<>();
        for (Video video: videos)
            results.add(new UserVideoResult(user, video));
        return results;
    }

    @CrossOrigin
    @PassToken
    @GetMapping(value = "api/user_id={id}/favorites")
    @ResponseBody
    public List<UserFavoriteResult> getUserFavorites(@PathVariable("id") int id) {
        User user = userService.getByUserId(id);
        if (null == user)
            return null;
        List<Favorite> favorites = favoriteService.findAllByFavoriteOwner(user);
        List<UserFavoriteResult> results = new ArrayList<>();
        for (Favorite favorite: favorites) {
            Image image = favoriteContentService.getFavoriteImage(favorite);
            results.add(new UserFavoriteResult(user, favorite, image));

        }
        return results;
    }

    @CrossOrigin
    @PassToken
    @GetMapping(value = "api/user_id={id}/bangumis")
    @ResponseBody
    public List<BangumiResult> getUserFavoriteBangumis(@PathVariable("id") Integer id) {
        User user = userService.getByUserId(id);
        if (null == user) return null;
        List<Bangumi> bangumis = favoriteContentService.findUserFavoriteBangumi(user);
        List<BangumiResult> results = new ArrayList<>();
        for (Bangumi bangumi: bangumis) {
            results.add(new BangumiResult(bangumi));
        }
        return results;
    }
}
