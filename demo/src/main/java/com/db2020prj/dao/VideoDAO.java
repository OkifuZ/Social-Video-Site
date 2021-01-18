package com.db2020prj.dao;

import com.db2020prj.entities.User;
import com.db2020prj.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoDAO extends JpaRepository<Video, Integer> {
    List<Video> findByVideoNameLike(String keyword);

    List<Video> findAllByVideoUploader(User uploader);
}
