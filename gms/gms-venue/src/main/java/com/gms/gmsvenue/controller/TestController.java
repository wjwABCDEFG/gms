package com.gms.gmsvenue.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "ok";
    }

    @RequestMapping("/delete")
    public void delete(int index) {
        System.out.println("hhhhhhhhhhhhh\t" + index);
    }
}
