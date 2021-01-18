package com.db2020prj.service;

import com.db2020prj.dao.FavoriteDAO;
import com.db2020prj.entities.Bangumi;
import com.db2020prj.entities.Favorite;
import com.db2020prj.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    FavoriteDAO favoriteDAO;

    public List<Favorite> findAllByFavoriteOwner(User owner) {
        return favoriteDAO.findAllByFavoriteOwner(owner);
    }

    public Favorite findFavoriteById(int id) {
        return favoriteDAO.findById(id).orElse(null);
    }

    public Favorite getOrCreateFavorite(String favoriteName, User user) {
        Favorite favorite = favoriteDAO.findByFavoriteOwnerAndFavoriteName(user, favoriteName);
        if (favorite != null) {
            return favorite;
        }
        favorite = new Favorite();
        favorite.setFavoritePermission(Favorite.FavoritePermission.PUBLIC);
        favorite.setFavoriteOwner(user);
        favorite.setFavoriteName(favoriteName);
        favoriteDAO.save(favorite);
        return favorite;
    }


}
