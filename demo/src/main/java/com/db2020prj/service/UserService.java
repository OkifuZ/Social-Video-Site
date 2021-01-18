package com.db2020prj.service;

import com.db2020prj.dao.UserDAO;
import com.db2020prj.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDao;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username) {
        return userDao.findByUsername(username);
    }

    public User get(String username, String userpassword){
        return userDao.getByUsernameAndPassword(username, userpassword);
    }

    public String add(User user) {
        if (null == getByName(user.getUsername())) {
            userDao.save(user);
            return "SUCCESS";
        }
        return "FAILED: username already exists";
    }

    public User getByUserId(int id) {
        return userDao.findById(id).orElse(null);
    }
}