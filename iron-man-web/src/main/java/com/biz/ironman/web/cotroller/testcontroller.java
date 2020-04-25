package com.biz.ironman.web.cotroller;

import com.biz.ironman.common.CommonCode;
import com.biz.ironman.common.CommonResponse;
import com.biz.ironman.common.ResultBuilder;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/18 15:53
 **/
@RestController
public class testcontroller {


    @Autowired
    UserService userService;


    @GetMapping("/success")
    public CommonResponse success(){
       return ResultBuilder.success();
    }

    @GetMapping("/success2")
    public CommonResponse success2(){
        String date = "hello world";
        return ResultBuilder.success(date);
    }


    @GetMapping("/fail")
    public CommonResponse fail(){
        return ResultBuilder.fail(CommonCode.USER_NOT_LOGIN);
    }


    @GetMapping("/exception")
    public CommonResponse ex() throws BusinessException {
        throw new BusinessException(CommonCode.UN_KNOW);
    }

}
