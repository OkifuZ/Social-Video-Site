package com.db2020prj.dao;

import com.db2020prj.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeDAO extends JpaRepository<Like, Integer> {
    public Like findByLikedBangumiAndLiker(Bangumi bangumi, User liker);

    public Like findByLikedVideoAndLiker(Video video, User liker);

    public Like findByLikedColumnAndLiker(Column column, User liker);

    public Like findByLikedDynamicAndLiker(Dynamic dynamic, User liker);

    public Like findByLikedCommentAndLiker(Comment comment, User liker);

    public List<Like> findByLiker(User liker);

    public List<Like> findByLikedBangumi(Bangumi bangumi);

    public List<Like> findByLikedVideo(Video video);

    public List<Like> findByLikedColumn(Column column);

    public List<Like> findByLikedDynamic(Dynamic dynamic);

    public List<Like> findByLikedComment(Comment comment);
}
