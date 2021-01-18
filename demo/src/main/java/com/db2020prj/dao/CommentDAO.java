package com.db2020prj.dao;

import com.db2020prj.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
    public List<Comment> findAllByCommentedBangumi(Bangumi bangumi);

    public List<Comment> findAllByCommentedVideo(Video video);

    public List<Comment> findAllByCommentedColumn(Column column);

    public List<Comment> findAllByCommentedDynamic(Dynamic dynamic);

    public List<Comment> findAllByCommentSender(User sender);
}
