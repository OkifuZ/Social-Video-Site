package com.db2020prj.dao;

import com.db2020prj.entities.Favorite;
import com.db2020prj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteDAO extends JpaRepository<Favorite, Integer> {
    List<Favorite> findAllByFavoriteOwner(User owner);

    Favorite findByFavoriteOwnerAndFavoriteName(User favoriteOwner, String favoriteName);
}
