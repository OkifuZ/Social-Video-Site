package com.db2020prj.service;

import com.db2020prj.dao.FavoriteContentDAO;
import com.db2020prj.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteContentService {
    @Autowired
    FavoriteContentDAO favoriteContentDAO;

    public int getFavoriteTimes(Object object) {
        List<FavoriteContent> res = null;
        if (Bangumi.class.equals(object.getClass())) {
            res = favoriteContentDAO.findAllByFcBangumi((Bangumi) object);
        } else if (Video.class.equals(object.getClass())) {
            res = favoriteContentDAO.findAllByFcVideo((Video) object);
        } else if (Column.class.equals(object.getClass())) {
            res = favoriteContentDAO.findAllByFcColumn((Column) object);
        }
        return null == res ? 0 : res.size();
    }

    public List<FavoriteContent> getFavoriteContentByFavorite(Favorite favorite) {
        return favoriteContentDAO.findAllByFcOwner(favorite);
    }

    public Image getFavoriteImage(Favorite favorite) {
        for (FavoriteContent favoriteContent: getFavoriteContentByFavorite(favorite)) {
            switch (favoriteContent.getFcType()) {
                case BANGUMI:
                    return favoriteContent.getFcBangumi().getBangumiImage();
                case COLUMN:
                    return favoriteContent.getFcColumn().getColumnImage();
                case VIDEO:
                    return favoriteContent.getFcVideo().getVideoImage();
            }
        }
        return null;
    }
    
    public void favoriteContent(Favorite favorite, Object content) {
        FavoriteContent favoriteContent = new FavoriteContent();
        favoriteContent.setFcOwner(favorite);
        if (Bangumi.class.equals(content.getClass())) {
            favoriteContent.setFcType(ReferenceContentType.BANGUMI);
            favoriteContent.setFcBangumi((Bangumi) content);
        } else if (Video.class.equals(content.getClass())) {
            favoriteContent.setFcType(ReferenceContentType.VIDEO);
            favoriteContent.setFcVideo((Video) content);
        } else if (Column.class.equals(content.getClass())) {
            favoriteContent.setFcType(ReferenceContentType.COLUMN);
            favoriteContent.setFcColumn((Column) content);
        }
        favoriteContentDAO.save(favoriteContent);
    }

    public List<Bangumi> findUserFavoriteBangumi(User user) {
        return favoriteContentDAO.findFcBangumiByFavoriteOwner(user);
    }

    public boolean alreadyFavored(Favorite favorite, Object content) {
        if (Bangumi.class.equals(content.getClass())) {
            return favoriteContentDAO.findByFcOwnerAndFcBangumi(favorite, (Bangumi) content) != null;
        } else if (Video.class.equals(content.getClass())) {
            return favoriteContentDAO.findByFcOwnerAndFcVideo(favorite, (Video) content) != null;
        } else if (Column.class.equals(content.getClass())) {
            return favoriteContentDAO.findByFcOwnerAndFcColumn(favorite, (Column) content) != null;
        }
        return false;
    }
}
