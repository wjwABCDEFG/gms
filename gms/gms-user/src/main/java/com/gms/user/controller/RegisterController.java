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

@RestController
@CrossOrigin
public class RegisterController {
    @Autowired
    UserService userService;

    @RequestMapping("/register")
    public String register(@RequestBody User user){
        userService.register(user.getUsername(),user.getRealname(), Encrypt.Encrypt_md5(user.getPassword()),user.getEmail(),user.getPhone());
        Result<User> result = new Result();
        result.setCode(200);
        //result.setData(us);
        result.setMsg("success");
        String resuleStr = JSON.toJSONString(result);
        return resuleStr;
    }
}
