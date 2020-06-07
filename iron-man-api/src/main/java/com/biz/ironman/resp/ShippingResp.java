package com.biz.ironman.resp;

import lombok.Data;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 17:36
 **/
@Data
public class ShippingResp {

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
