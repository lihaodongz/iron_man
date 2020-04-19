package com.biz.ironman.common;

import lombok.Data;

import javax.annotation.security.DenyAll;

/**
 * @author 李浩东
 * @description 返回对象基类
 * @date 2020/4/19 9:26
 **/
@Data
public class BaseCommon {

    private  String app;
    private  String operator;
}
