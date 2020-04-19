package com.biz.ironman.exception;

import com.biz.ironman.common.CommonCode;
import lombok.Data;

import javax.jnlp.IntegrationService;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 10:04
 **/
@Data
public class BusinessException extends Exception {


    private Integer code;
    private String msg;

    public BusinessException(String msg){
        super(msg);
        this.msg = msg;
    }

    public BusinessException(CommonCode commonCode){
        super(commonCode.getMsg());
        this.code = commonCode.getCode();
        this.msg = commonCode.getMsg();
    }

}
