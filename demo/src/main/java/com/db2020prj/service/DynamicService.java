package com.db2020prj.service;

import com.db2020prj.controllers.request.PostDynamicRequest;
import com.db2020prj.dao.DynamicDAO;
import com.db2020prj.entities.Dynamic;
import com.db2020prj.entities.Tag;
import com.db2020prj.entities.TagRelation;
import com.db2020prj.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicService {
    @Autowired
    DynamicDAO dynamicDAO;
    @Autowired
    TagService tagService;
    @Autowired
    TagRelationService tagRelationService;

    public void addDynamic(PostDynamicRequest request, User uploader) {
        Dynamic newDynamic = new Dynamic();
        newDynamic.setDynamicReadTimes(0);
        newDynamic.setDynamicPermission(2);
        newDynamic.setDynamicContent(request.getMomentText());
        newDynamic.setDynamicUploader(uploader);
        newDynamic.setDynamicLikeNumbers(0);
        dynamicDAO.save(newDynamic);
        for (String tagName: request.getNewMomentTags()) {
            Tag theTag = tagService.createOrGetTag(tagName);
            tagRelationService.addRelation(theTag, newDynamic);
        }
    }

    public List<Dynamic> findAllByDynamicUploader(User uploader) {
        return dynamicDAO.findAllByDynamicUploader(uploader);
    }

    public void deleteDynamic(Dynamic dynamic) {
        dynamicDAO.delete(dynamic);
    }

    public Dynamic getDynamicById(int id) {
        return dynamicDAO.findById(id).orElse(null);
    }
}
