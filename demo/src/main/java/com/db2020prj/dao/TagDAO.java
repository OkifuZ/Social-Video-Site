package com.db2020prj.dao;

import com.db2020prj.entities.FavoriteContent;
import com.db2020prj.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagDAO extends JpaRepository<Tag, Integer> {
    public Tag findByTagName(String tagName);

    public List<Tag> findAllByTagNameLike(String keyword);
}
