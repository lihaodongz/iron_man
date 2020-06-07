package com.biz.ironman.req;

import lombok.Data;

import javax.jnlp.IntegrationService;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 17:36
 **/
@Data
public class ShippingReq {

    private Integer id;
    private String receiverName;
    private String receiverPhone;
    private String receiverMobile;
    private String receiverProvince;
    private String receiverCity;
    private String receiverDistrict;
    private String receiverAddress;
    private String receiverZip;
}
