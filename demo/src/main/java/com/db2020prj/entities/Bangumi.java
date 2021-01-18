package com.db2020prj.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "bangumi")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@EntityListeners(AuditingEntityListener.class)
public class Bangumi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bangumi_id")
    int id;

    String bangumiName;
    @CreatedDate
    Date bangumiUploadDate;
    Integer bangumiPlayTimes;
    String bangumiContentUrl;
    String bangumiCategory;
    String bangumiDescription;
    Integer bangumiLikeNumbers;

    public Integer getBangumiLikeNumbers() {
        return bangumiLikeNumbers;
    }

    public void setBangumiLikeNumbers(Integer bangumiLikeNumbers) {
        this.bangumiLikeNumbers = bangumiLikeNumbers;
    }

    @ManyToOne
    @JoinColumn(name = "bangumi_image_id")
    Image bangumiImage;

    public Image getBangumiImage() {
        return bangumiImage;
    }

    public void setBangumiImage(Image bangumiImage) {
        this.bangumiImage = bangumiImage;
    }

    public void setBangumiContentUrl(String bangumiContentUrl) {
        this.bangumiContentUrl = bangumiContentUrl;
    }

    public String getBangumiDescription() {
        return bangumiDescription;
    }

    public void setBangumiDescription(String bangumiDescription) {
        this.bangumiDescription = bangumiDescription;
    }

    @ManyToOne
    @JoinColumn(name = "bangumi_uploader_id")
    private User bangumiUploader;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBangumiName() {
        return bangumiName;
    }

    public void setBangumiName(String bangumiName) {
        this.bangumiName = bangumiName;
    }

    public void setBangumiUploadDate(Date bangumiUploadDate) {
        this.bangumiUploadDate = bangumiUploadDate;
    }

    public Integer getBangumiPlayTimes() {
        return bangumiPlayTimes;
    }

    public void setBangumiPlayTimes(Integer bangumiPlayTimes) {
        this.bangumiPlayTimes = bangumiPlayTimes;
    }

    public User getBangumiUploader() {
        return bangumiUploader;
    }

    public void setBangumiUploader(User bangumiUploader) {
        this.bangumiUploader = bangumiUploader;
    }

    public Date getBangumiUploadDate() {
        return bangumiUploadDate;
    }

    public String getBangumiContentUrl() {
        return bangumiContentUrl;
    }

    public String getBangumiCategory() {
        return bangumiCategory;
    }

    public void setBangumiCategory(String bangumiCategory) {
        this.bangumiCategory = bangumiCategory;
    }
}
