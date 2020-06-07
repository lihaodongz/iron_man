package com.biz.ironman.web.goods;

import com.alibaba.fastjson.JSON;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.req.GetGoodsRep;
import com.biz.ironman.service.GoodsService;
import com.biz.ironman.vo.GoodsRespVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/6 16:23
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsServiceTest {

    @Autowired
    GoodsService goodsService;


    @Test
    public void testGoodsList() throws BusinessException {
        GetGoodsRep rep = new GetGoodsRep();
        //rep.setCategoryId(100002);
        rep.setPageNum(1);
        rep.setPageSize(1);
        List<GoodsRespVo> goodsByCate = goodsService.getGoodsByCate(rep);
        log.info(JSON.toJSONString(goodsByCate,true));
    }
}
