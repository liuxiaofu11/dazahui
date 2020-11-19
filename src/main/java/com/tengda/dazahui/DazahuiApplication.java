package com.tengda.dazahui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tengda.dazahui.system")
@MapperScan("com.tengda.dazahui.system.dao")
public class DazahuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DazahuiApplication.class, args);
        System.out.println("大杂烩项目启动成功");
    }

}

