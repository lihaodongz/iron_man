package com.biz.ironman.vo;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author 李浩东
 * @description
 * @date 2020/6/6 17:32
 **/
@Data
public class CartVo {

    private List<CartGoodsVo> cartGoodsVos;

    /**
     * 全选按钮
     */
    private Boolean selectAll;

    /**
     * 购物车总价,每一件商品的单价
     */
    private BigDecimal cartTotalPrice;

    /**
     * 购物车总数
     */
    private Integer cartTotalQuantity;

}
