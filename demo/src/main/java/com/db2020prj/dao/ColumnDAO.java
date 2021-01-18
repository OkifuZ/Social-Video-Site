package com.db2020prj.dao;

import com.db2020prj.entities.Column;
import com.db2020prj.entities.FavoriteContent;
import com.db2020prj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnDAO extends JpaRepository<Column, Integer> {
    List<Column> findByColumnNameLike(String keyword);

    List<Column> findAllByColumnUploader(User uploader);
}
