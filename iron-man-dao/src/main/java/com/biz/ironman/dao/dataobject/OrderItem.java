package com.biz.ironman.dao.dataobject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.OrientationRequested;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 11:32
 **/
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long orderNo;
    private Integer productId;
    private String productName;
    private String productImage;
    private BigDecimal currentUnitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Date createTime;
    private Date updateTime;
}
