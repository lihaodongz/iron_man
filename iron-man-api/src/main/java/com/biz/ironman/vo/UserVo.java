package com.biz.ironman.vo;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 12:10
 **/
@Data
public class UserVo {

    @NotBlank(message = "username 不能为空格")
    private String username;
    @NotNull
    private String password;

    /**
     * 邮箱
     */
    private String email;


    private Integer role;


    public String vaild(){
        if (username == null ){
            return "username is null ";
        }else if (password == null){
            return "password is null ";
        }else if (email == null){
            return "email is null ";
        }
        return "success";
    }
}
