package com.gms.gmseqpt.controller;

import com.alibaba.fastjson.JSON;
import com.gms.commons.domain.EqptMenu;
import com.gms.gmseqpt.dao.EqptMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
@RestController
public class EqptMenuController {
    @Autowired
    EqptMenuDao eqptMenuDao;

    @CrossOrigin
    @RequestMapping("/menus")
    public String getAllMenus(){
        System.out.println("访问成功");
        HashMap<String,Object> data = new HashMap<>();
        List<EqptMenu> menus = eqptMenuDao.getEqptMenus();
        System.out.println(menus);
        data.put("menus",menus);
        data.put("status",200);
        String s = JSON.toJSONString(data);
        return s;
    }
}
