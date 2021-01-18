package com.db2020prj.service;

import com.db2020prj.dao.TagRelationDAO;
import com.db2020prj.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.type.ReferenceType;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagRelationService {
    @Autowired
    TagRelationDAO tagRelationDAO;

    public List<Tag> findAllByTaggedContent(Object content) {
        List<TagRelation> tagRelations = new ArrayList<TagRelation>();
        if (Bangumi.class.equals(content.getClass())) {
            tagRelations = tagRelationDAO.findAllByTaggedBangumi((Bangumi) content);
        } else if (Video.class.equals(content.getClass())) {
            tagRelations = tagRelationDAO.findAllByTaggedVideo((Video) content);
        } else if (Column.class.equals(content.getClass())) {
            tagRelations = tagRelationDAO.findAllByTaggedColumn((Column) content);
        } else if (Dynamic.class.equals(content.getClass())) {
            tagRelations = tagRelationDAO.findAllByTaggedDynamic((Dynamic) content);
        }
        List<Tag> res = new ArrayList<>();
        for (TagRelation tagRelation: tagRelations) {
            res.add(tagRelation.getTag());
        }
        return res;
    }

    public void addRelation(Tag tag, Object content) {
        TagRelation relation = new TagRelation();
        relation.setTag(tag);
        if (Bangumi.class.equals(content.getClass())) {
            relation.setTaggedType(ReferenceContentType.BANGUMI);
            relation.setTaggedBangumi((Bangumi) content);
        } else if (Video.class.equals(content.getClass())) {
            relation.setTaggedType(ReferenceContentType.VIDEO);
            relation.setTaggedVideo((Video) content);
        } else if (Column.class.equals(content.getClass())) {
            relation.setTaggedType(ReferenceContentType.COLUMN);
            relation.setTaggedColumn((Column) content);
        } else if (Dynamic.class.equals(content.getClass())) {
            relation.setTaggedType(ReferenceContentType.DYNAMIC);
            relation.setTaggedDynamic((Dynamic) content);
        }
        tagRelationDAO.save(relation);
    }

    public List<TagRelation> getRelationsByTag(Tag tag) {
        return tagRelationDAO.findAllByTag(tag);
    }
}
