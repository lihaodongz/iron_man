package com.biz.ironman.enums;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 14:24
 **/
public enum  GoodEnum {
    ON_SALE(1,"在售状态"),
    OFF_SALE(2,"下架状态"),
    ON_DELETE(3,"删除")
    ;
    private Integer code;
    private String desc;

    GoodEnum() {
    }

    GoodEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
