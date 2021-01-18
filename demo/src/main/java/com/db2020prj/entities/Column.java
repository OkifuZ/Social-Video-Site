package com.db2020prj.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`column`")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@EntityListeners(AuditingEntityListener.class)
public class Column {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "column_id")
    int id;

    @CreatedDate
    Date columnUploadDate;

    public void setColumnUploadDate(Date columnUploadDate) {
        this.columnUploadDate = columnUploadDate;
    }

    String columnName;

    public Date getColumnUploadDate() {
        return columnUploadDate;
    }

    public void setColumnReadTimes(Integer columnReadTimes) {
        this.columnReadTimes = columnReadTimes;
    }

    Integer columnReadTimes;
    String columnContent;
    Integer columnLikeNumbers;

    public Integer getColumnLikeNumbers() {
        return columnLikeNumbers;
    }

    public void setColumnLikeNumbers(Integer columnLikeNumbers) {
        this.columnLikeNumbers = columnLikeNumbers;
    }

    @ManyToOne
    @JoinColumn(name = "column_uploader_id")
    private User columnUploader;

    @ManyToOne
    @JoinColumn(name = "column_image_id")
    private Image columnImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getColumnReadTimes() {
        return columnReadTimes;
    }

    public void setColumnReadTimes(int columnReadTimes) {
        this.columnReadTimes = columnReadTimes;
    }

    public String getColumnContent() {
        return columnContent;
    }

    public void setColumnContent(String columnContent) {
        this.columnContent = columnContent;
    }

    public User getColumnUploader() {
        return columnUploader;
    }

    public void setColumnUploader(User columnUploader) {
        this.columnUploader = columnUploader;
    }

    public Image getColumnImage() {
        return columnImage;
    }

    public void setColumnImage(Image columnImage) {
        this.columnImage = columnImage;
    }
}
