package com.biz.ironman.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 李浩东
 * @description 购物车中每一件商品
 * @date 2020/6/6 17:31
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartGoodsVo {

    private Integer goodsId;
    private String goodsName;
    /**
     * 购买的数量，
     */
    private Integer quantity;
    private String goodsSubtitle;
    private String goodsMainImage;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品状态
     */
    private Integer goodsStatus;
    /**
     * quantity*goodsprice
     */
    private BigDecimal goodsTotalPrice;

    private Integer goodsStock;

    private Boolean goodsSelected;

}
