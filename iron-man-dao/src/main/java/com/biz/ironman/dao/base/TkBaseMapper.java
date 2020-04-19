package com.biz.ironman.dao.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/18 17:48
 **/
public interface TkBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
