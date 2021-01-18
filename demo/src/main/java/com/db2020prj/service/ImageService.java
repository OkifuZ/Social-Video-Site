package com.db2020prj.service;

import com.db2020prj.dao.ImageDAO;
import com.db2020prj.entities.Image;
import com.db2020prj.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageDAO imageDAO;

    public Image createOrGetImage(String imageUrl, User uploader) {
        Image toFind = imageDAO.findByImageUrlAndImageUploader(imageUrl, uploader);
        if (null != toFind)
            return toFind;
        Image newImage = new Image();
        newImage.setImageName("noName");
        newImage.setImageUploader(uploader);
        newImage.setImageUrl(imageUrl);
        imageDAO.save(newImage);
        return imageDAO.findByImageUrlAndImageUploader(imageUrl, uploader);
    }

    public Image getImageById(int id) {
        return imageDAO.findById(id).orElse(null);
    }
}
