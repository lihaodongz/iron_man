package com.biz.ironman.service;

import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.req.CartReq;
import com.biz.ironman.vo.CartVo;


/**
 * @author 李浩东
 * @description
 * @date 2020/6/6 17:51
 **/
public interface CartService {


     CartVo add(Integer uid,CartReq req) throws BusinessException;


     CartVo fetchCart(Integer uid);

     /**
      * 更新购物车，数量和选中
      * 必填项为uid和goodsId 为必填项。
      */
     CartVo   updateCartGood(Integer uid ,CartReq req) throws BusinessException;


     /**
      * uid 和 req.goodsid 为必填项
      * @param uid
      * @param req
      * @return
      */
     CartVo  deleteCartVo(Integer uid,CartReq req) throws BusinessException;


     CartVo selectAll(Integer uid) throws BusinessException;

     CartVo notSelectAll(Integer uid) throws BusinessException;

     Integer sum(Integer uid) throws BusinessException;
}
