package com.biz.ironman.service;

import com.biz.ironman.dao.dataobject.User;
import com.biz.ironman.exception.BusinessException;
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

    boolean login(UserVo userVo) throws BusinessException;

    void registry(UserVo userVo) throws BusinessException;


}

