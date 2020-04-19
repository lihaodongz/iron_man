package com.biz.ironman.exception;

import com.biz.ironman.common.CommonCode;
import com.biz.ironman.common.CommonResponse;
import com.biz.ironman.common.ResultBilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
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
            log.error("发生异常，{}",e.getMessage());
            BusinessException exception = (BusinessException)e;
            return ResultBilder.fail(exception);
        }
        return ResultBilder.fail(CommonCode.UN_KNOW);
    }
}
