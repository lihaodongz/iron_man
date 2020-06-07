package com.biz.ironman.web.cotroller;

import com.alibaba.fastjson.JSON;
import com.biz.ironman.common.CommonCode;
import com.biz.ironman.common.CommonResponse;
import com.biz.ironman.common.ResultBuilder;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.service.UserService;
import com.biz.ironman.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;


/**
 * @author 李浩东
 * @description
 * @date 2020/6/6 10:58
**/
@RestController
public class UserController {


    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;




    @RequestMapping(value = "/user/registry",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse registry(@RequestBody UserVo userVo) throws BusinessException {
        logger.info("/user/registry 请求参数为:{}", JSON.toJSONString(userVo,true));
        String vaildResult = userVo.vaild();
        if (!"success".equals(vaildResult)){
            return ResultBuilder.fail(CommonCode.PARAMETER_ERROR,vaildResult);
        }
        try {
            userService.registry(userVo);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        return ResultBuilder.success();
    }


    @PostMapping(value = "/user/login")
    @ResponseBody
    public CommonResponse login(@Valid @RequestBody UserVo userVo,
                                BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()){
            logger.error("参数有误,{}",bindingResult.getFieldError().getDefaultMessage());
          throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        try {
            boolean login = userService.login(userVo);
            if (login == true){
                return ResultBuilder.success();
            }else{
                return ResultBuilder.fail(CommonCode.USER_ERROR);
            }

        }catch (Exception e){
            logger.error("com.biz.ironman.service.UserService#login 异常 异常信息为:{}",e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }
}
