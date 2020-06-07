package com.biz.ironman.service.Impl;

import com.alibaba.fastjson.JSON;
import com.biz.ironman.common.CommonCode;
import com.biz.ironman.common.Constants;
import com.biz.ironman.dao.dataobject.User;
import com.biz.ironman.dao.mapper.UserMapper;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.service.UserService;
import com.biz.ironman.utils.MD5Utils;
import com.biz.ironman.vo.UserVo;
import com.sun.deploy.net.proxy.pac.PACFunctions;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.awt.windows.ThemeReader;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author 李浩东
 * @description
 * @date 2020/4/18 15:38
 **/
@Service
@Slf4j
public class UserServiceImpl  implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    UserMapper userMapper;

    @Override
    public User fetchByUsername(UserVo userVo) {
        User user =null;
        String username = userVo.getUsername();
        if(Constants.space.equals(username) || username !=null){
            user = User.builder().username(username).build();
            user = userMapper.selectOne(user);
        }
        return user;
    }

    @Override
    public List<User> fetchUserList() {
        return userMapper.selectAll();
    }

    @Override
    public boolean login(UserVo userVo) throws BusinessException {
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        if (username == null || password == null){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        User build = User.builder().username(username).build();
        try {
            User user = userMapper.selectOne(build);
            if (user  != null){
                if (user.getPassword().equals(MD5Utils.digest(password))){
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            logger.error("com.biz.ironman.dao.mapper.UserMapper#selectone 执行失败 {}",e.getMessage());
            return false;
        }
    }

    @Override
    public void  registry(UserVo userVo) throws BusinessException {
        if (userVo == null){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        String password = userVo.getPassword();
        String digest = MD5Utils.digest(password);
        User build = User.builder().username(userVo.getUsername()).password(digest).email(userVo.getEmail()).build();
        if (userVo.getRole() == null){
            build.setRole(1);  // 默认是普通用户
        }
        try {
            log.info("com.biz.ironman.dao.mapper.UserMapper#insert 请求参数:{}", JSON.toJSONString(build,true));
            userMapper.insert(build);
        }catch (Exception e){
            log.info("com.biz.ironman.dao.mapper.UserMapper#insert 发生异常 :{}",e.getMessage());
        }
    }
}

