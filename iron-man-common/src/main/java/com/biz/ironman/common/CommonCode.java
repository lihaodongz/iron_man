package com.biz.ironman.common;

import lombok.Data;
import sun.security.provider.certpath.PKIXTimestampParameters;

import javax.jnlp.IntegrationService;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 9:35
 **/

public enum  CommonCode {

    PARAMETER_ERROR(10003,"参数异常"),
    UN_KNOW(10002,"未知异常"),
    USER_NOT_LOGIN(20000,"用户未登陆"),
    USER_ERROR(20001,"用户名或者密码错误"),
    SUCCESS(10000,"SUCCESS");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    CommonCode(){};

    CommonCode(Integer code, String msg){
        this.code =code;
        this.msg = msg;
    }
}
