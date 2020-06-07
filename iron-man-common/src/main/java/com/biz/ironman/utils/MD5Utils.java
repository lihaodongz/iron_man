package com.biz.ironman.utils;

import org.springframework.util.DigestUtils;

/**
 * @author 李浩东
 * @description MD5工具类
 * @date 2020/6/6 11:46
 **/
public class MD5Utils {

    public static String digest(String source){
        return DigestUtils.md5DigestAsHex(source.getBytes());
    }
}
