package com.biz.ironman.dao.mapper;

import com.biz.ironman.dao.base.TkBaseMapper;
import com.biz.ironman.dao.dataobject.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/18 16:48
 **/
@Repository
public interface UserMapper extends TkBaseMapper<User> {

    User findByUsername(String username);

}
