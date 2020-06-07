package com.biz.ironman.web.cotroller;

import com.biz.ironman.common.CommonCode;
import com.biz.ironman.common.CommonResponse;
import com.biz.ironman.common.ResultBuilder;
import com.biz.ironman.req.CartReq;
import com.biz.ironman.service.CartService;
import com.biz.ironman.vo.CartVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/6 17:44
 **/
@RestController
public class CartController {

    Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    CartService cartService;


    @PostMapping("/add/carts")
    public CommonResponse<CartVo> add(@Valid @RequestBody CartReq req)  {
        // todo uid 进行包装，BaseController
        try {
            CartVo add = cartService.add(1, req);
            return ResultBuilder.success(add);
        }catch (Exception e){
            logger.error("com.biz.ironman.web.cotroller.CartController#add 发生异常{}",e.getMessage());
            return ResultBuilder.fail(CommonCode.REQUEST_QUCKILY);
        }
    }


    @GetMapping("/fetch/cart")
    public CommonResponse<CartVo> fetchCart(){
        try {
            CartVo add = cartService.fetchCart(1);
            return ResultBuilder.success(add);
        }catch (Exception e){
            logger.error("com.biz.ironman.web.cotroller.CartController#add 发生异常{}",e.getMessage());
            return ResultBuilder.fail(CommonCode.REQUEST_QUCKILY);
        }
    }

    @PostMapping("/update/art")
    public CommonResponse<CartVo> updateCart(@RequestBody CartReq req){
        try {
            CartVo add = cartService.updateCartGood(1,req);
            return ResultBuilder.success(add);
        }catch (Exception e){
            logger.error("com.biz.ironman.web.cotroller.CartController#add 发生异常{}",e.getMessage());
            return ResultBuilder.fail(CommonCode.REQUEST_QUCKILY);
        }
    }

    @PostMapping("/delete/art")
    public CommonResponse<CartVo> deleteCart(@RequestBody CartReq req){
        try {
            CartVo add = cartService.deleteCartVo(1,req);
            return ResultBuilder.success(add);
        }catch (Exception e){
            logger.error("com.biz.ironman.web.cotroller.CartController#add 发生异常{}",e.getMessage());
            return ResultBuilder.fail(CommonCode.REQUEST_QUCKILY);
        }
    }

    @GetMapping("/update/selectAll")
    public CommonResponse<CartVo> updateCartAll(){
        try {
            CartVo add = cartService.selectAll(1);
            return ResultBuilder.success(add);
        }catch (Exception e){
            logger.error("com.biz.ironman.web.cotroller.CartController#add 发生异常{}",e.getMessage());
            return ResultBuilder.fail(CommonCode.REQUEST_QUCKILY);
        }
    }
    @GetMapping("/update/notSelectAll")
    public CommonResponse<CartVo> notSelectAll(){
        try {
            CartVo add = cartService.notSelectAll(1);
            return ResultBuilder.success(add);
        }catch (Exception e){
            logger.error("com.biz.ironman.web.cotroller.CartController#add 发生异常{}",e.getMessage());
            return ResultBuilder.fail(CommonCode.REQUEST_QUCKILY);
        }
    }

    @GetMapping("/fetch/sum")
    public CommonResponse<CartVo> fetchSum(){
        try {
            Integer sum = cartService.sum(1);
            return ResultBuilder.success(sum);
        }catch (Exception e){
            logger.error("com.biz.ironman.web.cotroller.CartController#add 发生异常{}",e.getMessage());
            return ResultBuilder.fail(CommonCode.REQUEST_QUCKILY);
        }
    }
}
