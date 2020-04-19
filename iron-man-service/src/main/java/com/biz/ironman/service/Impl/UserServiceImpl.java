package com.biz.ironman.service.Impl;

import com.biz.ironman.common.Constants;
import com.biz.ironman.dao.dataobject.User;
import com.biz.ironman.dao.mapper.UserMapper;
import com.biz.ironman.service.UserService;
import com.biz.ironman.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/18 15:38
 **/
@Service
public class UserServiceImpl  implements UserService {

    @Resource
    UserMapper userMapper;


    @Override
    public List<User> testController() {
        return userMapper.selectAll();
    }

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
    public Integer login(User user) {
        return null;
    }

    @Override
    public Integer registry(User user) {
        return null;
    }
}
