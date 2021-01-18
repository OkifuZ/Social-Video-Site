package com.db2020prj.dao;

import com.db2020prj.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TagRelationDAO extends JpaRepository<TagRelation, Integer> {
    public List<TagRelation> findAllByTaggedBangumi(Bangumi bangumi);

    public List<TagRelation> findAllByTaggedVideo(Video video);

    public List<TagRelation> findAllByTaggedColumn(Column column);

    public List<TagRelation> findAllByTaggedDynamic(Dynamic dynamic);

    public List<TagRelation> findAllByTag(Tag tag);
}
