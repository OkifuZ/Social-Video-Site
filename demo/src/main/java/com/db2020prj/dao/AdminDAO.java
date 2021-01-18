package com.db2020prj.dao;

import com.db2020prj.entities.Admin;
import com.db2020prj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDAO extends JpaRepository<Admin, Integer> {
    public Admin findByUserId(User user);
    Admin findByAdminId(Integer id);
}
