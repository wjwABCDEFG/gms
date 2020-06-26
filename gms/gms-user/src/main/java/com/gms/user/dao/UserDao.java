package com.gms.user.dao;

import com.gms.commons.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    public List<User> login(@Param("username") String username, @Param("password") String password);
    public void register(@Param("username") String username,@Param("realname") String realname, @Param("password") String password,@Param("email") String email,@Param("phone") String phone);
    public List<User> getAllUserList();
    public List<User> selectData(@Param("user_id") int user_id);
    public void updateUserData(@Param("user_id") int user_id,@Param("username") String username,@Param("realname") String realname,
                               @Param("password") String password,@Param("email") String email,@Param("phone") String phone);
    public void updateRole(@Param("user_id") int user_id,@Param("role_id") int role_id);
    public List<User> selectRole(@Param("role_id") String role_id);
}
