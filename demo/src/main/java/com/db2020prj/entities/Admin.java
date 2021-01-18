package com.db2020prj.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Column;

@Entity
@Table(name = "admin")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Admin {
    @Id
    @javax.persistence.Column(name = "admin_id")
    Integer adminId;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
    @OneToOne
    @JoinColumn(name = "admin_id")
    User userId;

    String adminName;

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public AdminPermmission getAdminPermission() {
        return adminPermission;
    }

    public void setAdminPermission(AdminPermmission adminPermission) {
        this.adminPermission = adminPermission;
    }

    AdminPermmission adminPermission;

    public enum AdminPermmission {
        ROOT,
        SUPER,
        COMMON
    }

}

