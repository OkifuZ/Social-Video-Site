package com.db2020prj.service;

import com.db2020prj.dao.TagDAO;
import com.db2020prj.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    TagDAO tagDAO;

    public Tag createOrGetTag(String tagName) {
        Tag toFind = tagDAO.findByTagName(tagName);
        if (null != toFind) return toFind;
        Tag newTag = new Tag();
        newTag.setTagName(tagName);
        tagDAO.save(newTag);
        return tagDAO.findByTagName(tagName);
    }

    public List<Tag> findTagsByKeyword(String keyword) {
        return tagDAO.findAllByTagNameLike("%" + keyword + "%");
    }

    public Tag findTagById(Integer id) {
        return tagDAO.findById(id).orElse(null);
    }
}
