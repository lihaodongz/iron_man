package com.biz.ironman.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author 李浩东
 * @description 返回对象
 * @date 2020/4/19 9:26
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonResponse <T>  implements Serializable {

    private Integer code;
    private Boolean success;
    private String msg;
    private T data;
}
