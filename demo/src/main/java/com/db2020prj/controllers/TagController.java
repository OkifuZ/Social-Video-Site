package com.db2020prj.controllers;

import com.db2020prj.controllers.result.Result;
import com.db2020prj.controllers.result.TagRelatedContentResult;
import com.db2020prj.entities.Tag;
import com.db2020prj.entities.TagRelation;
import com.db2020prj.jwt.PassToken;
import com.db2020prj.service.TagRelationService;
import com.db2020prj.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    TagRelationService tagRelationService;

    @CrossOrigin
    @PassToken
    @GetMapping(value = "api/tags/keyword={keyword}")
    @ResponseBody
    public List<Tag> getTagsByKeyword(@PathVariable("keyword") String keyword) {
        return tagService.findTagsByKeyword(keyword);
    }

    @CrossOrigin
    @PassToken
    @GetMapping(value = "api/tag-related-content/tagId={id}")
    @ResponseBody
    public Result<TagRelatedContentResult> getTagRelatedContent(@PathVariable("id") Integer id) {
        System.out.println("controller reached.");
        Tag tag = tagService.findTagById(id);
        if (null == tag) {
            return new Result(404, "tag not exists");
        }
        List<TagRelation> tagRelations = tagRelationService.getRelationsByTag(tag);
        return new Result(new TagRelatedContentResult(tag, tagRelations), 200, "tag related contents found.");
    }
}
