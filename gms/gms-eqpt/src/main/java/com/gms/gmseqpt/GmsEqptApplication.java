package com.gms.gmseqpt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.gms.gmseqpt.dao")
@SpringBootApplication
public class GmsEqptApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmsEqptApplication.class, args);
    }

}
