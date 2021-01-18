package com.db2020prj.service;

import com.db2020prj.dao.*;
import com.db2020prj.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    LikeDAO likeDAO;
    @Autowired
    BangumiDAO bangumiDAO;
    @Autowired
    VideoDAO videoDAO;
    @Autowired
    CommentDAO commentDAO;
    @Autowired
    ColumnDAO columnDAO;
    @Autowired
    DynamicDAO dynamicDAO;

    public Like getLikeByUserAndContent(User user, Object content) {
        if (Bangumi.class.equals(content.getClass()))
            return likeDAO.findByLikedBangumiAndLiker((Bangumi) content, user);
        if (Video.class.equals(content.getClass()))
            return likeDAO.findByLikedVideoAndLiker((Video) content, user);
        if (Dynamic.class.equals(content.getClass()))
            return likeDAO.findByLikedDynamicAndLiker((Dynamic) content, user);
        if (Column.class.equals(content.getClass()))
            return likeDAO.findByLikedColumnAndLiker((Column) content, user);
        if (Comment.class.equals(content.getClass()))
            return likeDAO.findByLikedCommentAndLiker((Comment) content, user);
        return null;
    }

    public void likeContent(User user, Object content) {
        Like like = new Like();
        like.setLiker(user);
        if (Bangumi.class.equals(content.getClass())) {
            like.setLikedType(ReferenceContentType.BANGUMI);
            like.setLikedBangumi((Bangumi) content);
            ((Bangumi) content).setBangumiLikeNumbers(((Bangumi) content).getBangumiLikeNumbers() + 1);
            bangumiDAO.save((Bangumi) content);
        } else if (Video.class.equals(content.getClass())) {
            like.setLikedType(ReferenceContentType.VIDEO);
            like.setLikedVideo((Video) content);
            ((Video) content).setVideoLikeNumbers(((Video) content).getVideoLikeNumbers() + 1);
            videoDAO.save((Video) content);
        } else if (Dynamic.class.equals(content.getClass())) {
            like.setLikedType(ReferenceContentType.DYNAMIC);
            like.setLikedDynamic((Dynamic) content);
            ((Dynamic) content).setDynamicLikeNumbers(((Dynamic) content).getDynamicLikeNumbers() + 1);
            dynamicDAO.save((Dynamic) content);
        } else if (Column.class.equals(content.getClass())){
            like.setLikedType(ReferenceContentType.COLUMN);
            like.setLikedColumn((Column) content);
            ((Column) content).setColumnLikeNumbers(((Column) content).getColumnLikeNumbers() + 1);
            columnDAO.save((Column) content);
        } else if (Comment.class.equals(content.getClass())) {
            like.setLikedType(ReferenceContentType.COMMENT);
            like.setLikedComment((Comment) content);
            ((Comment) content).setCommentLikeNumbers(((Comment) content).getCommentLikeNumbers() + 1);
            commentDAO.save((Comment) content);
        }
        likeDAO.save(like);
    }

    public void DislikeContent(Like dislike) {
        likeDAO.delete(dislike);
    }
}
