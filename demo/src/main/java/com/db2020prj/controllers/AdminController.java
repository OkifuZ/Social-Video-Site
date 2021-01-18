package com.db2020prj.controllers;

import com.db2020prj.controllers.result.Result;
import com.db2020prj.entities.Bangumi;
import com.db2020prj.entities.Column;
import com.db2020prj.entities.Dynamic;
import com.db2020prj.entities.Video;
import com.db2020prj.jwt.AsAdmin;
import com.db2020prj.jwt.NeedToken;
import com.db2020prj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    VideoService videoService;
    @Autowired
    ColumnService columnService;
    @Autowired
    BangumiService bangumiService;
    @Autowired
    DynamicService dynamicService;

    @CrossOrigin
    @DeleteMapping(value = "api/admin/delete/videoId={id}")
    @NeedToken
    @AsAdmin
    @ResponseBody
    public Result deleteVideo(@PathVariable("id") Integer id) {
        Video video = videoService.getVideoById(id);
        if (null == video)
            return new Result(404, "Video not exists.");
        videoService.deleteVideo(video);
        return new Result(200, "Delete complete.");
    }

    @CrossOrigin
    @DeleteMapping(value = "api/admin/delete/bangumiId={id}")
    @NeedToken
    @AsAdmin
    @ResponseBody
    public Result deleteBangumi(@PathVariable("id") Integer id) {
        Bangumi bangumi = bangumiService.getBangumiById(id);
        if (null == bangumi)
            return new Result(404, "Bangumi not exists.");
        bangumiService.deleteBangumi(bangumi);
        return new Result(200, "Delete complete.");
    }

    @CrossOrigin
    @DeleteMapping(value = "api/admin/delete/columnId={id}")
    @NeedToken
    @AsAdmin
    @ResponseBody
    public Result deleteColumn(@PathVariable("id") Integer id) {
        Column column = columnService.getColumnById(id);
        if (null == column)
            return new Result(404, "Column not exists.");
        columnService.deleteColumn(column);
        return new Result(200, "Delete complete.");
    }

    @CrossOrigin
    @DeleteMapping(value = "api/admin/delete/dynamicId={id}")
    @NeedToken
    @AsAdmin
    @ResponseBody
    public Result deleteDynamic(@PathVariable("id") Integer id) {
        Dynamic dynamic = dynamicService.getDynamicById(id);
        if (null == dynamic)
            return new Result(404, "Dynamic not exists.");
        dynamicService.deleteDynamic(dynamic);
        return new Result(200, "Delete complete.");
    }

}
