package com.db2020prj.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "favorite")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "favorite_id")
    int id;

    String favoriteName;
    FavoritePermission favoritePermission;

    @ManyToOne
    @JoinColumn(name = "favorite_owner_id")
    User favoriteOwner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    public FavoritePermission getFavoritePermission() {
        return favoritePermission;
    }

    public void setFavoritePermission(FavoritePermission favoritePermission) {
        this.favoritePermission = favoritePermission;
    }

    public User getFavoriteOwner() {
        return favoriteOwner;
    }

    public void setFavoriteOwner(User favoriteOwner) {
        this.favoriteOwner = favoriteOwner;
    }

    public enum FavoritePermission {
        PRIVATE,
        PUBLIC
    }
}


