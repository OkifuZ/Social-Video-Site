package com.db2020prj.service;
import com.db2020prj.controllers.request.PostBangumiRequest;
import com.db2020prj.dao.BangumiDAO;
import com.db2020prj.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BangumiService {
    @Autowired
    BangumiDAO bangumiDAO;
    @Autowired
    TagRelationService tagRelationService;
    @Autowired
    TagService tagService;

    public List<Bangumi> findAll() {
        return bangumiDAO.findAll();
    }

    public Bangumi getBangumiById(int id) { return bangumiDAO.findById(id).orElse(null); }

    public List<Bangumi> findAllByNameLike(String keyword) {
        return bangumiDAO.findByBangumiNameLike(keyword);
    }

    public void deleteBangumi(Bangumi bangumi) {
        bangumiDAO.delete(bangumi);
    }

    public void postBangumi(PostBangumiRequest request, User uploader, Image image) {
        Bangumi bangumi = new Bangumi();
        bangumi.setBangumiCategory(request.getBangumiCategory());
        bangumi.setBangumiContentUrl(request.getBangumiUrl());
        bangumi.setBangumiDescription(request.getBangumiDes());
        bangumi.setBangumiName(request.getBangumiTitle());;
        bangumi.setBangumiPlayTimes(0);
        bangumi.setBangumiUploader(uploader);
        bangumi.setBangumiImage(image);
        bangumi.setBangumiLikeNumbers(0);
        bangumiDAO.save(bangumi);
        List<String> tags = request.getNewBangumiTags();
        for (String tagName: tags) {
            Tag theTag = tagService.createOrGetTag(tagName);
            tagRelationService.addRelation(theTag, bangumi);
        }
    }

}
