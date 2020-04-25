package com.biz.ironman.common;

import com.biz.ironman.exception.BusinessException;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 9:33
 **/
public class ResultBuilder {



    public static CommonResponse success(){
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        response.setCode((CommonCode.SUCCESS.getCode()));
        response.setMsg(CommonCode.SUCCESS.getMsg());
        return response;
    }

    public static <T> CommonResponse success(T data){
        CommonResponse<T> response = new CommonResponse<T>();
        response.setSuccess(true);
        response.setCode((CommonCode.SUCCESS.getCode()));
        response.setMsg(CommonCode.SUCCESS.getMsg());
        response.setData(data);
        return response;
    }

    public static CommonResponse fail(CommonCode commonCode){
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setCode(commonCode.getCode());
        response.setMsg(commonCode.getMsg());
        return response;
    }


    public static CommonResponse fail(BusinessException commonCode){
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setCode(commonCode.getCode());
        response.setMsg(commonCode.getMsg());
        return response;
    }

    public static CommonResponse fail(CommonCode commonCode,String msg){
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setCode(commonCode.getCode());
        response.setMsg(msg);
        return response;
    }

}
