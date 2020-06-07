package com.biz.ironman.service;

import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.req.ShippingReq;
import com.biz.ironman.resp.ShippingResp;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 17:22
 **/
public interface ShippingService {

    /**
     * 添加地址 uid+shippingInfo
     * @param uid
     * @param req
     * @return
     */
   Integer addShipping(Integer uid,ShippingReq req) throws BusinessException;


    /**
     * id+ shippingid
     * @param uid
     * @param req
     */
    void  deleteShipping(Integer uid,ShippingReq req) throws BusinessException;


    /**
     * 更新地址信息 uid+ id + 修改信息项
     * @param uid
     * @param req
     */
    void  updateShipping(Integer uid,ShippingReq req) throws BusinessException;

    /**
     * 获取用户的地址列表。分页
     * @param uid
     */
    List<ShippingResp> fetchShipping(Integer uid, Integer pageNum, Integer pageSize) throws BusinessException;

}
