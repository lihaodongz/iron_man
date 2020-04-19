package com.biz.ironman.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/18 15:53
 **/
@SpringBootApplication(scanBasePackages = {"com.biz.ironman"})
@MapperScan("com.biz.ironman.dao.mapper")
public class IornManApplication {

    public static void main(String[] args) {
        SpringApplication.run(IornManApplication.class,args);
    }
}
