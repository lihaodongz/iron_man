package com.biz.ironman.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/6 17:43
 **/
@Data

public class CartReq {

    @NotNull(message = "商品id不能为空")
    private Integer goodId;
    private Boolean selected = true;

    /**
     * 更新的数量
     */
    private Integer quantity;

    public CartReq() {
    }

    public CartReq(@NotNull(message = "商品id不能为空") Integer goodId) {
        this.goodId = goodId;
    }

    public CartReq(@NotNull(message = "商品id不能为空") Integer goodId, Boolean selected) {
        this.goodId = goodId;
        this.selected = selected;
    }

    public CartReq(@NotNull(message = "商品id不能为空") Integer goodId, Boolean selected, Integer quantity) {
        this.goodId = goodId;
        this.selected = selected;
        this.quantity = quantity;
    }
}
