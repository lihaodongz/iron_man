package com.biz.ironman.dao.dataobject.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.validation.constraints.NotNull;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 14:38
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRedis {

    // id
    private Integer productId;
    // 数量
    private Integer quantity;
    // 是否选中
    private Boolean producteSelected;


}
