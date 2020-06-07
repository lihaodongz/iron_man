package com.biz.ironman.dao.mapper;

import com.biz.ironman.dao.base.TkBaseMapper;
import com.biz.ironman.dao.dataobject.Shipping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 17:28
 **/
@Repository
public interface ShippingMapper extends TkBaseMapper<Shipping> {


    List<Shipping> fetchWithPaginator(@Param("userName") String userName,@Param("start") Integer start,@Param("offet") Integer offet);

}
