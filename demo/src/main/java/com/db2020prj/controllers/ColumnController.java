package com.db2020prj.controllers;

import com.db2020prj.controllers.request.PostColumnRequest;
import com.db2020prj.controllers.result.ColumnContentResult;
import com.db2020prj.controllers.result.Result;
import com.db2020prj.entities.Column;
import com.db2020prj.controllers.result.ColumnResult;
import com.db2020prj.entities.History;
import com.db2020prj.entities.Image;
import com.db2020prj.entities.User;
import com.db2020prj.jwt.NeedToken;
import com.db2020prj.jwt.OptionalToken;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ColumnController {
    @Autowired
    ColumnService columnService;
    @Autowired
    FavoriteContentService favoriteContentService;
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;
    @Autowired
    CommentService commentService;
    @Autowired
    TagRelationService tagRelationService;
    @Autowired
    HistoryService historyService;
    @Autowired
    LikeService likeService;

    @CrossOrigin
    @GetMapping(value = "api/columns")
    @PassToken
    @ResponseBody
    public List<ColumnResult> getColumns() {
        List<Column> columns = columnService.findAll();
        List<ColumnResult> result = new ArrayList<ColumnResult>();
        for (Column column: columns) {
            result.add(new ColumnResult(column));
        }
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "api/columns/top8")
    @PassToken
    @ResponseBody
    public List<ColumnResult> getColumnsTop8() {
        List<Column> columns = columnService.findAll();
        Map<Column, Integer> favoriteTimes = new HashMap<Column, Integer>();
        for (Column column: columns) {
            favoriteTimes.put(column, favoriteContentService.getFavoriteTimes(column));
        }
        Collections.sort(columns, new Comparator<Column>() {
            @Override
            public int compare(Column column, Column t1) {
                return - favoriteTimes.get(column) + favoriteTimes.get(t1);
            }
        });
        List<ColumnResult> result = new ArrayList<>();
        for (Column column: columns) {
            result.add(new ColumnResult(column));
            if (result.size() >= 8) break;
        }
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "/api/columns/keyword/{keyword}")
    @PassToken
    @ResponseBody
    public List<ColumnResult> getColumnsByKeyword(@PathVariable("keyword") String keyword) {
        List<Column> columns = columnService.findAllByNameLike("%" + keyword + "%");
        List<ColumnResult> result = new ArrayList<ColumnResult>();
        for (Column column: columns) {
            result.add(new ColumnResult(column));
        }
        return result;
    }

    @CrossOrigin
    @PostMapping(value = "api/columns/post")
    @NeedToken
    @ResponseBody
    public Result postColumn(@RequestBody PostColumnRequest request) {
        String text = request.getColumnText();
        if (null == text || text.length() == 0)
            return new Result(300, "Content cannot be blank!");
        String coverImageUrl = request.getColumnPicUrl();
        if (null == coverImageUrl || coverImageUrl.length() == 0)
            return new Result(300, "Must have a cover image!");
        User uploader = userService.getByUserId(request.getUserId());
        Image coverImage = imageService.createOrGetImage(coverImageUrl, uploader);
        columnService.addColumn(request, uploader, coverImage);
        return new Result(200, "Dynamic posted successfully.");

    }

    @CrossOrigin
    @GetMapping(value = "api/columns/id={id}")
//    @PassToken
    @OptionalToken
    @ResponseBody
    public Result<ColumnContentResult> getColumnById(@PathVariable("id") Integer id, @RequestParam(required = false) Integer userId) {
        Column column = columnService.getColumnById(id);
        if (null == column)
            return new Result(404, "Column Not Found!");
        boolean liked = false;
        if (null != userId) {
            User watcher = userService.getByUserId(userId);
            historyService.watchContent(watcher, column);
            liked = (null != likeService.getLikeByUserAndContent(watcher, column));
        }
        return new Result(new ColumnContentResult(column, liked, tagRelationService, commentService), 200, "Column resource found.");

    }
}
