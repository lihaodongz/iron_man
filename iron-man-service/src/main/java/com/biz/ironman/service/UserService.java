package com.biz.ironman.service;

import com.biz.ironman.dao.dataobject.User;
import com.biz.ironman.vo.UserVo;

import java.rmi.registry.Registry;
import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/18 15:38
 **/
public interface UserService {

    User fetchByUsername(UserVo userVo);

    List<User> fetchUserList();

    Integer login(User user);

    Integer registry(User user);


}

