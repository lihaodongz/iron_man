package com.biz.ironman.dao.dataobject;

import lombok.Data;

import javax.jnlp.IntegrationService;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 17:23
 **/
@Data
@Table(name = "mall_shipping")
public class Shipping {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId;
    private String receiverName;
    private String receiverPhone;
    private String receiverMobile;
    private String receiverProvince;
    private String receiverCity;
    private String receiverDistrict;
    private String receiverAddress;
    private String receiverZip;
    private String createTime;
    private String updateTime;
}
