package com.db2020prj.dao;

import com.db2020prj.entities.Dynamic;
import com.db2020prj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DynamicDAO extends JpaRepository<Dynamic, Integer> {
    List<Dynamic> findAllByDynamicUploader(User uploader);
}
