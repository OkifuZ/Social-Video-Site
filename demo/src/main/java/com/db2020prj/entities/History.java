package com.db2020prj.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@EntityListeners(AuditingEntityListener.class)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "history_id")
    int id;

    @CreatedDate
    Date watchDate;

    ReferenceContentType watchedType;

    @ManyToOne
    @JoinColumn(name = "watcher_id")
    User watcher;

    @ManyToOne
    @JoinColumn(name = "watched_column_id")
    Column watchedColumn;

    @ManyToOne
    @JoinColumn(name = "watched_bangumi_id")
    Bangumi watchedBangumi;

    @ManyToOne
    @JoinColumn(name = "watched_video_id")
    Video watchedVideo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(Date watchDate) {
        this.watchDate = watchDate;
    }

    public ReferenceContentType getWatchedType() {
        return watchedType;
    }

    public void setWatchedType(ReferenceContentType watchedType) {
        this.watchedType = watchedType;
    }

    public User getWatcher() {
        return watcher;
    }

    public void setWatcher(User watcher) {
        this.watcher = watcher;
    }

    public Column getWatchedColumn() {
        return watchedColumn;
    }

    public void setWatchedColumn(Column watchedColumn) {
        this.watchedColumn = watchedColumn;
    }

    public Bangumi getWatchedBangumi() {
        return watchedBangumi;
    }

    public void setWatchedBangumi(Bangumi watchedBangumi) {
        this.watchedBangumi = watchedBangumi;
    }

    public Video getWatchedVideo() {
        return watchedVideo;
    }

    public void setWatchedVideo(Video watchedVideo) {
        this.watchedVideo = watchedVideo;
    }
}

