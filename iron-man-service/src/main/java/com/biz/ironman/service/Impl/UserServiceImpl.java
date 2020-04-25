package com.biz.ironman.service.Impl;

import com.biz.ironman.common.CommonCode;
import com.biz.ironman.common.Constants;
import com.biz.ironman.dao.dataobject.User;
import com.biz.ironman.dao.mapper.UserMapper;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.service.UserService;
import com.biz.ironman.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        User user = userMapper.selectOne(build);
        if (user  != null){
            if (user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void  registry(UserVo userVo) throws BusinessException {
        if (userVo == null){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        User build = User.builder().username(userVo.getUsername()).password(userVo.getPassword()).build();
        try {
            userMapper.insert(build);
        }catch ( Exception e){
            log.error(e.getMessage());
        }
    }
}

