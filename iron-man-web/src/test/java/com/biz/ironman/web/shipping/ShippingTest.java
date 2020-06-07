package com.biz.ironman.web.shipping;

import com.alibaba.fastjson.JSON;
import com.biz.ironman.dao.dataobject.Shipping;
import com.biz.ironman.dao.mapper.ShippingMapper;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.resp.ShippingResp;
import com.biz.ironman.service.ShippingService;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/7 17:30
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShippingTest {


    Logger logger = LoggerFactory.getLogger(ShippingTest.class);


    @Autowired
    ShippingMapper shippingMapper;

    @Autowired
    ShippingService shippingService;



    @Test
    public void testAdd(){
        Shipping shipping = new Shipping();
        shipping.setUserId(String.valueOf(9));
        shipping.setReceiverAddress("浙江杭州西湖区留下街道");
        shipping.setReceiverCity("浙江杭州");
        shipping.setReceiverDistrict("西湖区");
        shipping.setReceiverMobile("15834066705");
        shipping.setReceiverMobile("15834066705");
        shipping.setReceiverPhone("15834066705");
        shipping.setReceiverZip("030031");
        shipping.setReceiverProvince("浙江省");
        shipping.setReceiverName("李浩东");
        shippingMapper.insert(shipping);
    }


    @Test
    public void testFetch() throws BusinessException {
        List<ShippingResp> shippingResps = shippingService.fetchShipping(9, 1, 1);
        logger.info("result:{}", JSON.toJSONString(shippingResps,true));
    }
}
