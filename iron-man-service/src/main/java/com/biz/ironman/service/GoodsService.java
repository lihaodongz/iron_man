package com.biz.ironman.service;

import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.req.GetGoodsRep;
import com.biz.ironman.vo.GoodsRespVo;
import org.omg.CosNaming.IstringHelper;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/25 12:10
 **/
public interface GoodsService {

    List<GoodsRespVo> getGoodsByCate(GetGoodsRep request) throws BusinessException;

    GoodsRespVo getGoodsById(Integer id);

}
