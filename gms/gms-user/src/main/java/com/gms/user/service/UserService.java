package com.gms.user.service;

import com.gms.commons.domain.User;
import com.gms.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> login(String username, String password){
        List<User> us = userDao.login(username,password);
        return us;
    }

    public List<User> selectData(int user_id){
        List<User> us = userDao.selectData(user_id);
        return us;
    }

    public void register(String username,String realname,String password,String email,String phone){
        userDao.register(username,realname,password,email,phone);
    }

    public List<User> getAllUserList(){
        List<User> us = userDao.getAllUserList();
        return us;
    }

    public void updateUserData(int user_id,String username,String realname, String password,String email,String phone){
        userDao.updateUserData(user_id,username,realname,password,email,phone);
    }

    public void updateRole(int user_id,int role_id){
        userDao.updateRole(user_id,role_id);
    }

    public List<User> selectRole(String role_id){
        List<User> us = userDao.selectRole(role_id);
        return us;
    }
}
