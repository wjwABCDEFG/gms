package com.gms.user.controller;

import com.alibaba.fastjson.JSON;
import com.gms.commons.domain.User;
import com.gms.commons.utils.Encrypt;
import com.gms.commons.utils.Result;
import com.gms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getUserList")
    public String getUserList(){
        Result<List> result = new Result<>();
        List<User> users =userService.getAllUserList();
        result.setCode(200);
        result.setMsg("返回用户列表");
        result.setData(users);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/updateUserData")
    public String updateUserData(@RequestBody User user){
        Result<List> result = new Result<>();
        userService.updateUserData(user.getUser_id(),user.getUsername(),user.getRealname(), Encrypt.Encrypt_md5(user.getPassword()),user.getEmail(),user.getPhone());
        List<User> us = userService.selectData(user.getUser_id());
        result.setCode(200);
        result.setMsg("资料修改成功");
        result.setData(us);
        String resultStr = JSON.toJSONString(result);
        System.out.println(resultStr);
        return resultStr;
    }

    @RequestMapping("/updateRole")
    public String updateRole(@RequestBody User user){
        Result<List> result = new Result<>();
        userService.updateRole(user.getUser_id(),user.getRole_id());
        List<User> users =userService.getAllUserList();
        result.setCode(200);
        result.setMsg("返回用户列表");
        result.setData(users);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }

    @RequestMapping("/selectRole")
    public String selectRole(@RequestBody String role_id) {
        Result<List> result = new Result<>();
        List<User> users =userService.selectRole(role_id);
        result.setCode(200);
        result.setMsg("返回用户列表");
        result.setData(users);
        String resultStr = JSON.toJSONString(result);
        return resultStr;
    }
}
