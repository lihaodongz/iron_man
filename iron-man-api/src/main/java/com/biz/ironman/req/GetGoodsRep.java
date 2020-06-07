package com.biz.ironman.req;

import lombok.Data;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/25 14:21
 **/
@Data
public class GetGoodsRep {
    /**
     * 类目id
     */
    private Integer categoryId;

    private Integer pageNum;

    private Integer pageSize;
}
