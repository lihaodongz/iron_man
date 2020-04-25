package com.biz.ironman.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/25 14:10
 **/
@Data
public class GoodsRespVo   {
    /**
     * 自增id
     */
    private Integer id;
    /**
     * 分类id，对应mall_category表的主键
     */
    private Integer categoryId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品的副标题
     */
    private String subtitle;
    /**
     * 产品主图,url相对地址
     */
    private String mainImage;
    /**
     * 图片地址,json格式,扩展用
     */

    private String subImages;

    private String detail;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 商品库存
     */
    private Integer stock;
    /**
     * 商品状态， '商品状态.1-在售 2-下架 3-删除'
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private  Date updateTime;

}
