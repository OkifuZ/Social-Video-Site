package com.db2020prj.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dynamic")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@EntityListeners(AuditingEntityListener.class)
public class Dynamic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "dynamic_id")
    int id;

    @CreatedDate
    Date dynamicUploadDate;

    Integer dynamicReadTimes;
    DynamicPermission dynamicPermission;
    String dynamicContent;
    Integer dynamicLikeNumbers;

    public void setDynamicReadTimes(Integer dynamicReadTimes) {
        this.dynamicReadTimes = dynamicReadTimes;
    }

    public void setDynamicPermission(DynamicPermission dynamicPermission) {
        this.dynamicPermission = dynamicPermission;
    }

    public Integer getDynamicLikeNumbers() {
        return dynamicLikeNumbers;
    }

    public void setDynamicLikeNumbers(Integer dynamicLikeNumbers) {
        this.dynamicLikeNumbers = dynamicLikeNumbers;
    }

    @ManyToOne
    @JoinColumn(name = "dynamic_uploader_id")
    User dynamicUploader;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDynamicUploadDate() {
        return dynamicUploadDate;
    }

    public void setDynamicUploadDate(Date dynamicUploadDate) {
        this.dynamicUploadDate = dynamicUploadDate;
    }

    public int getDynamicReadTimes() {
        return dynamicReadTimes;
    }

    public void setDynamicReadTimes(int dynamicReadTimes) {
        this.dynamicReadTimes = dynamicReadTimes;
    }

    public DynamicPermission getDynamicPermission() {
        return dynamicPermission;
    }

    public void setDynamicPermission(int level) {
        switch (level) {
            case 0:
                this.dynamicPermission = DynamicPermission.PRIVATE;
                break;
            case 1:
                this.dynamicPermission = DynamicPermission.FOLLOWER_ONLY;
                break;
            default:
                this.dynamicPermission = DynamicPermission.PUBLIC;
                break;
        }
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public User getDynamicUploader() {
        return dynamicUploader;
    }

    public void setDynamicUploader(User dynamicUploader) {
        this.dynamicUploader = dynamicUploader;
    }

    public enum DynamicPermission {
        PRIVATE,
        PUBLIC,
        FOLLOWER_ONLY
    }

}

