package com.biz.ironman.dao.mapper;

import com.biz.ironman.dao.base.TkBaseMapper;
import com.biz.ironman.dao.dataobject.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/18 16:48
 **/
@Repository
public interface UserMapper extends TkBaseMapper<User> {

    User findByUsername(String username);

    int insert(@Param("user") User user);

}
