package com.biz.ironman.common;



/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 9:35
 **/

public enum  CommonCode {

    DB_ERROR(10004,"数据库执行失败"),
    PARAMETER_ERROR(10003,"参数异常"),
    UN_KNOW(10002,"未知异常"),
    USER_NOT_LOGIN(20000,"用户未登陆"),
    USER_ERROR(20001,"用户名或者密码错误"),
    SUCCESS(10000,"SUCCESS"),
    GOODS_NOT_EXISTS(10005,"商品不存在"),
    GOODS_OFF_OR_DELETED(10006,"商品删除或者下架"),
    GOODS_OFF_STOCK(10007,"商品销量不煮"),
    REQUEST_QUCKILY(10008,"请求太快了，稍等下呗"),
    USER_INFO_ERROR(10009,"用户信息错误"),

    ;

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
