package com.db2020prj.dao;

import com.db2020prj.entities.Image;
import com.db2020prj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageDAO extends JpaRepository<Image, Integer> {
    public Image findByImageUrlAndImageUploader(String imageUrl, User imageUploader);

    public List<Image> findAllByImageUploader(User imageUploader);
}
