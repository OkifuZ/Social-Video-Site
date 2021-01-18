package com.db2020prj.dao;

import com.db2020prj.entities.Bangumi;
import com.db2020prj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BangumiDAO extends JpaRepository<Bangumi, Integer>{
    List<Bangumi> findByBangumiNameLike(String keyword);

}
