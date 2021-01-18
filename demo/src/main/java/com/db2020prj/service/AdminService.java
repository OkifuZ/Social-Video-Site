package com.db2020prj.service;

import com.db2020prj.dao.AdminDAO;
import com.db2020prj.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminDAO adminDAO;

    public boolean isAdmin(User user) {
        System.out.println(user.getId());
//        return adminDAO.findByAdminId(user.getId()) != null;
        return adminDAO.findByUserId(user) != null;
    }
}
