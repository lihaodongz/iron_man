package com.biz.ironman.dao.dataobject;

import sun.security.provider.certpath.PKIXTimestampParameters;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 11:16
 **/
@Table(name = "mall_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    private Long orderNo;
    /**
     *
     */
    private Long userId;
    /**
     *
     */
    private Long shippingId;
    /**
     * 实际付款金额
     */
    private BigDecimal payment;
    /**
     * 支付类型
     */
    private Integer paymentType;
    /**
     * 运费
     */
    private Integer postage;
    /**
     * 订单状态
     * 0-已取消- 10-未付款，20-已付款，待发货  40-已发货，待确认收获
     * 50 确认收获，退换期  60 交易成功 70：交易关闭
     */
    private Integer status;
    /**
     * 支付时间
     */
    private Date paymentTime;
    /**
     * 发货时间
     */
    private Date sendTime;
    /**
     * 交易开始时间
     */
    private Date endTime;
    /**
     * 交易关闭时间
     */
    private Date closeTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
