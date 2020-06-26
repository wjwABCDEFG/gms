package com.gms.gmsvenue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.gms.gmsvenue.dao")
public class GmsVenueApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmsVenueApplication.class, args);
    }

}
