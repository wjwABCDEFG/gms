package com.gms.user.controller;

import com.alibaba.fastjson.JSON;
import com.gms.commons.domain.MainMenu;
import com.gms.user.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MenuController {

    @Autowired
    MenuDao menuDao;

    @RequestMapping("/menus")
    public String getALLMenu(){
        HashMap<String,Object> data = new HashMap<>();
        int status = 404;
        List<MainMenu> menus = menuDao.getMenus();
        if(menus!=null){
            data.put("data",menus);
            data.put("flag",200);
        }else{
            data.put("flag",404);
        }
        return JSON.toJSONString(data);


//        Result<User> result = new Result();
//        int status = 404;//404失败 200成功
//        result.setMsg("success");
    }
}
