package com.db2020prj.controllers;

import com.db2020prj.controllers.request.PostVideoRequest;
import com.db2020prj.controllers.request.TokenInfo;
import com.db2020prj.controllers.request.UserInfo;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.controllers.result.VideoContentResult;
import com.db2020prj.entities.History;
import com.db2020prj.entities.Image;
import com.db2020prj.entities.User;
import com.db2020prj.entities.Video;
import com.db2020prj.controllers.result.VideoResult;
import com.db2020prj.jwt.NeedToken;
import com.db2020prj.jwt.OptionalToken;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class VideoController {
    @Autowired
    VideoService videoService;
    @Autowired
    FavoriteContentService favoriteContentService;
    @Autowired
    TagRelationService tagRelationService;
    @Autowired
    CommentService commentService;
    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;
    @Autowired
    LikeService likeService;

    @CrossOrigin
    @GetMapping(value = "/api/videos")
    @PassToken
    @ResponseBody
    public List<VideoResult> getVideos() {
        List<Video> videos = videoService.findAll();
        List<VideoResult> result = new ArrayList<>();
        for (Video video: videos) {
            result.add(new VideoResult(video));
        }
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "/api/videos/top8")
    @PassToken
    @ResponseBody
    public List<VideoResult> getVideosTop8() {
        List<Video> videos = videoService.findAll();
        Map<Video, Integer> favoriteTimes = new HashMap<Video, Integer>();
        for (Video video: videos) {
            favoriteTimes.put(video, favoriteContentService.getFavoriteTimes(video));
        }
        Collections.sort(videos, new Comparator<Video>() {
            @Override
            public int compare(Video video, Video t1) {
                return - favoriteTimes.get(video) + favoriteTimes.get(t1);
            }
        });
        List<VideoResult> result = new ArrayList<>();
        for (Video video: videos) {
            result.add(new VideoResult(video));
            if (result.size() >= 8) break;
        }
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "/api/videos/keyword/{keyword}")
    @PassToken
    @ResponseBody
    public List<VideoResult> getVideosByKeyword(@PathVariable("keyword") String keyword) {
        List<Video> videos = videoService.findAllByNameLike("%" + keyword + "%");
        List<VideoResult> result = new ArrayList<VideoResult>();
        for (Video video: videos) {
            result.add(new VideoResult(video));
        }
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "/api/videos/av={id}")
    @OptionalToken
//    @PassToken
    @ResponseBody
    public Result<VideoContentResult> getVideoById(@PathVariable("id") Integer id, @RequestParam(required = false) Integer userId) {
        System.out.println("Controller reached. Id = " + id);
        Video video = videoService.getVideoById(id);
        if (null != video) {
            boolean liked = false;
            if (null != userId) {
                User watcher = userService.getByUserId(userId);
                historyService.watchContent(watcher, video);
                liked = (null != likeService.getLikeByUserAndContent(watcher, video));
            }
            return new Result(new VideoContentResult(video, liked, tagRelationService, commentService), 200, "Video found.");
        } else return new Result(404, "resource not found.");
    }

    @CrossOrigin
    @PostMapping(value = "/api/videos/post")
    @NeedToken
    @ResponseBody
    public Result postVideo(@RequestBody PostVideoRequest request) {
        String videoName = request.getVideoTitle();
        String imageUrl = request.getVideoPicUrl();
        if (videoName.length() == 0)
            return new Result(300, "Must have video name!");
        if (videoName.length() > 50)
            return new Result(300, "video name too long!");
        if (imageUrl == null || imageUrl.length() == 0)
            return new Result(300, "must have a cover picture!");
        int userId = request.getUserId();
        User user = userService.getByUserId(userId);
        Image image = imageService.createOrGetImage(request.getVideoPicUrl(), user);
        videoService.addVideo(request, user, image);
        return new Result(200, "video successfully posted!");
    }

}
