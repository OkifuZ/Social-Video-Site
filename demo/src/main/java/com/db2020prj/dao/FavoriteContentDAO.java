package com.db2020prj.dao;

import com.db2020prj.entities.*;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

public interface FavoriteContentDAO extends JpaRepository<FavoriteContent, Integer> {
    List<FavoriteContent> findAllByFcColumn(Column column);

    List<FavoriteContent> findAllByFcVideo(Video video);

    List<FavoriteContent> findAllByFcBangumi(Bangumi bangumi);

    List<FavoriteContent> findAllByFcOwner(Favorite favoriteOwener);

    @Query(value = "SELECT FC.fcBangumi FROM FavoriteContent FC JOIN FC.fcOwner fo WHERE fo.favoriteOwner = :favoriteOwner")
    List<Bangumi> findFcBangumiByFavoriteOwner(User favoriteOwner);

    FavoriteContent findByFcOwnerAndFcBangumi(Favorite fcOwner, Bangumi fcBangumi);

    FavoriteContent findByFcOwnerAndFcVideo(Favorite fcOwner, Video fcVideo);

    FavoriteContent findByFcOwnerAndFcColumn(Favorite fcOwner, Column column);

}
