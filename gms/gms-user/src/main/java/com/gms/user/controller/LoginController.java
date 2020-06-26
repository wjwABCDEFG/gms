package com.gms.user.controller;

import com.alibaba.fastjson.JSON;
import com.gms.commons.domain.User;
import com.gms.commons.utils.Encrypt;
import com.gms.commons.utils.Result;
import com.gms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(@RequestBody User user){
        List<User> us = userService.login(user.getUsername(), Encrypt.Encrypt_md5(user.getPassword()));
        Result<List> result = new Result();
        System.out.println(us);
        if(us.size()<1) {
            result.setCode(404);
            result.setMsg("fail");
            String resuleStr = JSON.toJSONString(result);
            return resuleStr;
        }else{
            result.setCode(200);
            result.setData(us);
            result.setMsg("success");
            String resuleStr = JSON.toJSONString(result);
            return resuleStr;
        }
    }
}
