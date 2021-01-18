package com.db2020prj.service;

import com.db2020prj.controllers.request.PostCommentRequest;
import com.db2020prj.dao.CommentDAO;
import com.db2020prj.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDAO commentDAO;

    public List<Comment> findAllByCommentedContent(Object content) {
        List<Comment> res = new ArrayList<>();
        if (Bangumi.class.equals(content.getClass())) {
            res = commentDAO.findAllByCommentedBangumi((Bangumi) content);
        } else if (Video.class.equals(content.getClass())) {
            res = commentDAO.findAllByCommentedVideo((Video) content);
        } else if (Column.class.equals(content.getClass())) {
            res = commentDAO.findAllByCommentedColumn((Column) content);
        } else if (Dynamic.class.equals(content.getClass())) {
            res = commentDAO.findAllByCommentedDynamic((Dynamic) content);
        }
        return res;
    }

    public void postComment(User user, String content, Object target) {
        Comment comment = new Comment();
        comment.setCommentContent(content);
        comment.setCommentSender(user);
        comment.setCommentLikeNumbers(0);
        if (Bangumi.class.equals(target.getClass())) {
            comment.setCommentedType(ReferenceContentType.BANGUMI);
            comment.setCommentedBangumi((Bangumi) target);
        } else if (Video.class.equals(target.getClass())) {
            comment.setCommentedType(ReferenceContentType.VIDEO);
            comment.setCommentedVideo((Video) target);
        } else if (Column.class.equals(target.getClass())) {
            comment.setCommentedType(ReferenceContentType.COLUMN);
            comment.setCommentedColumn((Column) target);
        } else if (Dynamic.class.equals(target.getClass())) {
            comment.setCommentedType(ReferenceContentType.DYNAMIC);
            comment.setCommentedDynamic((Dynamic) target);
        }
        commentDAO.save(comment);
    }

    public Comment getCommentById(Integer id) {
        return commentDAO.findById(id).orElse(null);
    }
}
