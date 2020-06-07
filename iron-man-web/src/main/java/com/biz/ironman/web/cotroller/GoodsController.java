package com.biz.ironman.web.cotroller;

import com.biz.ironman.common.CommonResponse;
import com.biz.ironman.common.ResultBuilder;
import com.biz.ironman.req.GetGoodsRep;
import com.biz.ironman.service.GoodsService;
import com.biz.ironman.service.UserService;
import com.biz.ironman.vo.GoodsRespVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.util.locale.provider.LocaleServiceProviderPool;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/25 15:05
 **/
@RestController
@Slf4j
public class GoodsController {


    Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private GoodsService goodsService;

    @GetMapping("/goodsList")
    public CommonResponse<List<GoodsRespVo>> getGoodsPage(@RequestParam("cateGoryId") Integer cateGoryId) {
        logger.info("=================="+cateGoryId);
        GetGoodsRep request = new GetGoodsRep();
        try {
            List<GoodsRespVo> goodsByCate = goodsService.getGoodsByCate(request);
            return ResultBuilder.success(goodsByCate);
        }catch (Exception e){
            log.error("goodsList method invoke fail message:{}",e.getMessage());
            return null;
        }
    }


    @GetMapping("/getDetail")
    public CommonResponse<GoodsRespVo> getDetail(@RequestParam("id") Integer id){
        if (id == null || id < 0){
            return ResultBuilder.success();
        }
        GoodsRespVo goodsById = goodsService.getGoodsById(id);
        return ResultBuilder.success(goodsById);
    }
}
