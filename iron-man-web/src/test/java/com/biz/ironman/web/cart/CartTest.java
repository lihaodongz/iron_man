package com.biz.ironman.web.cart;

import com.alibaba.fastjson.JSON;
import com.biz.ironman.common.ResultBuilder;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.req.CartReq;
import com.biz.ironman.service.CartService;
import com.biz.ironman.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 14:43
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartTest {

    @Autowired
    CartService cartService;

    @Test
    public void testAddCart() throws BusinessException {
        CartReq req = new CartReq(29, true);
        cartService.add(1, req);
    }

    @Test
    public void testFetchCart() throws BusinessException {
        CartVo cartVo = cartService.fetchCart(1);
        log.info(JSON.toJSONString(ResultBuilder.success(cartVo),true));

    }

    @Test
    public void testUpdateCart() throws BusinessException {
        CartReq req = new CartReq(29, false,10);
        CartVo cartVo = cartService.updateCartGood(1,req);
        log.info(JSON.toJSONString(ResultBuilder.success(cartVo),true));
    }

    @Test
    public void testDeleteCart() throws BusinessException {
        CartReq req = new CartReq(29);
        CartVo cartVo = cartService.deleteCartVo(1,req);
        log.info(JSON.toJSONString(ResultBuilder.success(cartVo),true));
    }


    @Test
    public void testSelectAllAndSelectAll() throws BusinessException { ;
        CartVo cartVo = cartService.notSelectAll(1);
        log.info(JSON.toJSONString(ResultBuilder.success(cartVo),true));
    }

    @Test
    public void testSum() throws BusinessException { ;
        Integer sum = cartService.sum(1);
        log.info(JSON.toJSONString(ResultBuilder.success(sum),true));
    }
}
