package com.db2020prj.controllers;

import com.db2020prj.controllers.request.PostBangumiRequest;
import com.db2020prj.controllers.result.BangumiContentResult;
import com.db2020prj.controllers.result.BangumiResult;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.entities.Bangumi;
import com.db2020prj.controllers.result.BangumisByCateResult;
import com.db2020prj.entities.Image;
import com.db2020prj.entities.User;
import com.db2020prj.jwt.AsAdmin;
import com.db2020prj.jwt.OptionalToken;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BangumiController {

    @Autowired
    BangumiService bangumiService;
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
    @GetMapping(value = "/api/bangumis/cate")
    @PassToken
    @ResponseBody
    public List<BangumisByCateResult> getBangumisByCate() {
        List<Bangumi> bangumis = bangumiService.findAll();
        Map<String, List<Bangumi>> bangumiMap = new HashMap<>();
        for (Bangumi bangumi: bangumis) {
            String category = bangumi.getBangumiCategory();
            if (!bangumiMap.containsKey(category)) {
                bangumiMap.put(category, new ArrayList<>());
            }
            bangumiMap.get(category).add(bangumi);
        }
        List<BangumisByCateResult> results = new ArrayList<>();
        bangumiMap.forEach((cateName, cate) -> {
            BangumisByCateResult result = new BangumisByCateResult(cateName, cate);
            results.add(result);
            /*for (Bangumi bangumi: cate) {
                System.out.println(bangumi.getBangumiName());
            }*/
        });
        return results;
    }

    @CrossOrigin
    @GetMapping(value = "/api/bangumis/top8")
    @PassToken
    @ResponseBody
    public List<BangumiResult> getBangumisTop8() {
        List<Bangumi> bangumis = bangumiService.findAll();
        Map<Bangumi, Integer> favoriteTimes = new HashMap<Bangumi, Integer>();
        for (Bangumi bangumi: bangumis) {
            favoriteTimes.put(bangumi, favoriteContentService.getFavoriteTimes(bangumi));
        }
        Collections.sort(bangumis, new Comparator<Bangumi>() {
            @Override
            public int compare(Bangumi bangumi, Bangumi t1) {
                return - favoriteTimes.get(bangumi) + favoriteTimes.get(t1);
            }
        });
        List<BangumiResult> res = new ArrayList<>();
        for (Bangumi bangumi: bangumis) {
            res.add(new BangumiResult(bangumi));
            if (res.size() >= 8) break;
        }
        /*for (Bangumi bangumi: res) {
            System.out.println(bangumi.getBangumiName());
        }*/
        return res;
    }

    @CrossOrigin
    @GetMapping(value = "/api/bangumis/keyword/{keyword}")
    @PassToken
    @ResponseBody
    public List<BangumiResult> getBangumisByKeyword(@PathVariable("keyword") String keyword) {
        List<Bangumi> bangumis = bangumiService.findAllByNameLike("%" + keyword + "%");
        List<BangumiResult> res = new ArrayList<>();
        for (Bangumi bangumi: bangumis) res.add(new BangumiResult(bangumi));
        return res;
    }

    @CrossOrigin
    @GetMapping(value = "/api/bangumis/id={id}")
//    @PassToken
    @OptionalToken
    @ResponseBody
    public Result<BangumiContentResult> getBangumiById(@PathVariable("id") Integer id, @RequestParam(required = false) Integer userId) {
        Bangumi bangumi = bangumiService.getBangumiById(id);
        if (null == bangumi)
            return new Result(404, "Bangumi not Found!");
        boolean liked = false;
        if (null != userId) {
            User watcher = userService.getByUserId(userId);
            historyService.watchContent(watcher, bangumi);
            liked = (null != likeService.getLikeByUserAndContent(watcher, bangumi));
        }
        return new Result(new BangumiContentResult(bangumi, liked, tagRelationService, commentService), 200, "Bangumi resource found");
    }

    @CrossOrigin
    @PostMapping(value = "/api/bangumis/post")
    @AsAdmin
    @ResponseBody
    public Result postBangumi(@RequestBody PostBangumiRequest request) {
        String videoName = request.getBangumiTitle();
        String imageUrl = request.getBangumiPicUrl();
        if (videoName.length() == 0)
            return new Result(300, "Must have bangumi name!");
        if (videoName.length() > 50)
            return new Result(300, "bangumi name too long!");
        if (imageUrl == null || imageUrl.length() == 0)
            return new Result(300, "must have a cover picture!");
        int userId = request.getUserId();
        User user = userService.getByUserId(userId);
        Image image = imageService.createOrGetImage(request.getBangumiPicUrl(), user);
        bangumiService.postBangumi(request, user, image);
        return new Result(200, "bangumi successfully posted!");
    }

}

