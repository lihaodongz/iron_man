package com.biz.ironman.dao.mapper;

import com.biz.ironman.dao.base.TkBaseMapper;
import com.biz.ironman.dao.dataobject.Good;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/25 14:23
 **/
@Repository
public interface GoodsMapper extends TkBaseMapper<Good> {


    List<Good> selectByCategorys(@Param("cateGoryIds") Set<Long> cateGoryIds);
}
