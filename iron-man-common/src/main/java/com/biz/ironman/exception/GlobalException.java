package com.biz.ironman.exception;

import com.biz.ironman.common.CommonCode;
import com.biz.ironman.common.CommonResponse;
import com.biz.ironman.common.ResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 10:07
 **/
@RestControllerAdvice
@Slf4j
public class GlobalException {


    @ExceptionHandler(BusinessException.class)
    public CommonResponse exceptionHandler(Exception e){
        if (e instanceof BusinessException){
            BusinessException exception = (BusinessException)e;
            return ResultBuilder.fail(exception);
        }
        // TODO: 2020/6/6  目前只处理businessexception 其他异常全部未知错误 
        return ResultBuilder.fail(CommonCode.UN_KNOW);
    }
}
