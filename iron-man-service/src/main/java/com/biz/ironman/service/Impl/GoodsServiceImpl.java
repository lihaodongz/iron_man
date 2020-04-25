package com.biz.ironman.service.Impl;

import com.biz.ironman.common.CommonCode;
import com.biz.ironman.dao.dataobject.Category;
import com.biz.ironman.dao.dataobject.Good;
import com.biz.ironman.dao.mapper.GoodsMapper;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.req.GetGoodsRep;
import com.biz.ironman.service.CategoryService;
import com.biz.ironman.service.GoodsService;
import com.biz.ironman.vo.GoodsRespVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForYear;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UnknownFormatConversionException;
import java.util.stream.Collectors;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/25 12:10
 **/
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {


    @Resource
    GoodsMapper goodsMapper;

    @Autowired
    CategoryService categoryService;

    /**
     * 代码李进行了强转所以，代码量要考虑解决categoryId == null 的情况
     * @param request
     * @return
     * @throws BusinessException
     */
    @Override
    public List<GoodsRespVo> getGoodsByCate(GetGoodsRep request) throws BusinessException {
        Integer categoryId = request.getCategoryId();
        log.info("categoryId :{}",categoryId);
        Set<Long> resultSet = new HashSet<>();
        categoryService.fetchSubCategory(Long.valueOf(categoryId),resultSet);
        resultSet.add(Long.valueOf(categoryId));
        List<Good> goods = goodsMapper.selectByCategorys(resultSet);
        List<GoodsRespVo> result = goods.stream().map(i->{
            GoodsRespVo goodsRespVo = GoodsServiceImpl.convert2goodsRespVo(i);
            return goodsRespVo;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public GoodsRespVo getGoodsById(Integer id) {
        Good good = null;
        try {
            good = goodsMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            log.error("获取商详失败:{}",e.getMessage());
        }
        return convert2goodsRespVo(good);
    }


    private static GoodsRespVo convert2goodsRespVo(Good good){
        GoodsRespVo goodsRespVo = new GoodsRespVo();
        // 拷贝双方必须实现get/set  序列化不一定？？？ source !=null
        try {
            BeanUtils.copyProperties(good,goodsRespVo);
        }catch (Exception e){
            log.error("拷贝信息失败:{}",e.getMessage());
            return null;
        }

        return goodsRespVo;
    }
}
