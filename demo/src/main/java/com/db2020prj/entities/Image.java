package com.db2020prj.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Column;

@Entity
@Table(name = "image")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})


public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    int id;

    String imageName;
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "image_uploader_id")
    private User imageUploader;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl ;
    }

    public User getImageUploader() {
        return imageUploader;
    }

    public void setImageUploader(User imageUploader) {
        this.imageUploader = imageUploader;
    }
}
