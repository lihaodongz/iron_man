package com.biz.ironman.service.Impl;

import com.alibaba.fastjson.JSON;
import com.biz.ironman.common.CommonCode;
import com.biz.ironman.dao.dataobject.Shipping;
import com.biz.ironman.dao.dataobject.User;
import com.biz.ironman.dao.mapper.ShippingMapper;
import com.biz.ironman.dao.mapper.UserMapper;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.req.ShippingReq;
import com.biz.ironman.resp.ShippingResp;
import com.biz.ironman.service.ShippingService;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 17:22
 **/
@Service
public class ShippingServiceImpl implements ShippingService {


    Logger logger = LoggerFactory.getLogger(ShippingServiceImpl.class);

    @Autowired
    ShippingMapper shippingMapper;

    @Autowired
    UserMapper userMapper;



    @Override
    public Integer addShipping(Integer uid, ShippingReq req) throws BusinessException {
        if (uid == null ){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(uid));
        if (user == null){
            throw new BusinessException(CommonCode.USER_INFO_ERROR);
        }
        Shipping shipping = new Shipping();
        shipping.setReceiverName(user.getUsername());
        BeanUtils.copyProperties(req,shipping);
        try {
            logger.info("com.biz.ironman.dao.mapper.ShippingMapper#insert 请求参数为:{}", JSON.toJSONString(shipping,true));
            int insert = shippingMapper.insert(shipping);
            return insert;
        }catch (Exception e){
            logger.info("com.biz.ironman.dao.mapper.ShippingMapper#insert 发生异常 {}",e.getMessage());
        }
        return 0;
    }

    @Override
    public void deleteShipping(Integer uid, ShippingReq req) throws BusinessException {
        if (uid == null ){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(uid));
        if (user == null){
            throw new BusinessException(CommonCode.USER_INFO_ERROR);
        }
        Shipping shipping = new Shipping();
        shipping.setReceiverName(user.getUsername());
        try {
            logger.info("com.biz.ironman.dao.mapper.ShippingMapper#delete 请求参数为:{}", JSON.toJSONString(shipping,true));
             shippingMapper.delete(shipping);
        }catch (Exception e){
            logger.info("com.biz.ironman.dao.mapper.ShippingMapper#delete 发生异常 {}",e.getMessage());
        }
    }

    @Override
    public void updateShipping(Integer uid, ShippingReq req) throws BusinessException {
        if (uid == null ){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(uid));
        if (user == null){
            throw new BusinessException(CommonCode.USER_INFO_ERROR);
        }
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(req,shipping);
        try {
            logger.info("com.biz.ironman.dao.mapper.ShippingMapper#updateByPrimaryKey 请求参数为:{}", JSON.toJSONString(shipping,true));
            shippingMapper.updateByPrimaryKey(shipping);
        }catch (Exception e){
            logger.info("com.biz.ironman.dao.mapper.ShippingMapper#updateByPrimaryKey 发生异常 {}",e.getMessage());
        }
    }

    @Override
    public List<ShippingResp> fetchShipping(Integer uid, Integer pageNum, Integer pageSize) throws BusinessException {
        if (uid == null ){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(uid));
        if (user == null){
            throw new BusinessException(CommonCode.USER_INFO_ERROR);
        }
        Integer start = (pageNum-1)*pageSize;
        Integer offet = pageSize;
        try {
            logger.info("com.biz.ironman.dao.mapper.ShippingMapper#select 请求参数为:{}", JSON.toJSONString(user.getUsername()));
            List<Shipping> shippings = shippingMapper.fetchWithPaginator(user.getUsername(), start, offet);
            List<ShippingResp> collect = shippings.stream().map(this::convertShippingResp).collect(Collectors.toList());
            return collect;
        }catch (Exception e){
            logger.info("com.biz.ironman.dao.mapper.ShippingMapper#select 发生异常 {}",e.getMessage());
        }
        return null;
    }

    private ShippingResp convertShippingResp(Shipping shipping){
        ShippingResp shippingResp = new ShippingResp();
        BeanUtils.copyProperties(shipping,shippingResp);
        return shippingResp;
    }
}
