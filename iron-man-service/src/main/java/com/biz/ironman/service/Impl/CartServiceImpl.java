package com.biz.ironman.service.Impl;

import com.alibaba.fastjson.JSON;
import com.biz.ironman.common.CommonCode;
import com.biz.ironman.dao.dataobject.Good;
import com.biz.ironman.dao.dataobject.redis.CartRedis;
import com.biz.ironman.dao.mapper.GoodsMapper;
import com.biz.ironman.enums.GoodEnum;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.req.CartReq;
import com.biz.ironman.service.CartService;
import com.biz.ironman.vo.CartGoodsVo;
import com.biz.ironman.vo.CartVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/6 17:51
 **/
@Service
public class CartServiceImpl implements CartService {

    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private final static String CART_REDIS_KEY = "CART_%d";

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 该接口主要是想购物车添加一个商品，添加时校验商品的数量，但是添加到购物车是否扣减库存，此时根据有业务考虑
     *
     * @param req
     * @return
     */
    @Override
    public CartVo add(Integer uid,CartReq req) throws BusinessException {
        // 商品是否存在。状态是否正常，库存是否足够
        Good goodsRespVo = goodsMapper.selectByPrimaryKey(req.getGoodId());
        if (goodsRespVo == null){
            throw new BusinessException(CommonCode.GOODS_NOT_EXISTS);
        }
        if (!goodsRespVo.getStatus().equals(GoodEnum.ON_SALE.getCode())){
            throw new BusinessException(CommonCode.GOODS_OFF_OR_DELETED);
        }
        if (goodsRespVo.getStock() <= 0){
            throw new BusinessException(CommonCode.GOODS_OFF_STOCK);
        }
        // key: UID value: goodsId,seletcted,quantity
        String redisKey = String.format(CART_REDIS_KEY, uid);
        CartRedis cartRedis = new CartRedis(req.getGoodId(),1,req.getSelected());
        HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
        //list 和Map 很相似，但是list 必须便利查找元素
        String value = opsForHash.get(redisKey, String.valueOf(req.getGoodId()));
        if (value == null){
            opsForHash.put(redisKey,String.valueOf(req.getGoodId()), JSON.toJSONString(cartRedis));
        }else{
            CartRedis newcartRedis = JSON.parseObject(value, CartRedis.class);
            newcartRedis.setQuantity(newcartRedis.getQuantity()+1);
            logger.info("cartRedis:{}",JSON.toJSONString(newcartRedis));
            opsForHash.put(redisKey,String.valueOf(req.getGoodId()), JSON.toJSONString(newcartRedis));
        }
        // key :uid value: json.tojsonstring()
        //redisTemplate.opsForValue().set(String.format(CART_REDIS_KEY,uid), JSON.toJSONString(cartRedis));
        // 写入Redis,并非全部信息存入redis，只保留关键的信息即可。
        // key: UID,Value:cartRedisVo
        // key:UID ，value：Map
        CartVo cartVo = fetchCart(uid);
        return cartVo;
    }

    /**
     * 获取购物车，redis中获取数据，然后组装数据即可
     * @param uid
     * @return
     */
    @Override
    public CartVo fetchCart(Integer uid) {
        CartVo cartVo = new CartVo();
        Boolean selectAll = true;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;
        Integer cartTotalQuantity = 0;
        List<CartGoodsVo> cartGoodsVos = new ArrayList<>();
        HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY,uid);
        Map<String, String> entries = opsForHash.entries(redisKey);
        // TODO: 2020/6/7  取出goodId 查询数据库 取出数据， 但是这里是在循环里请求数据库，查询成本太高了。此时可以优化逻辑，sql 使用in 查询即可
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            Integer key = Integer.valueOf(entry.getKey()); // 取出
            String value = entry.getValue();
            CartRedis cartRedis = JSON.parseObject(value, CartRedis.class);
            Good good = goodsMapper.selectByPrimaryKey(key);
            if (good != null ){
                CartGoodsVo cartGoodsVo = CartGoodsVo.builder()
                        .goodsId(good.getId())
                        .goodsName(good.getName())
                        .goodsMainImage(good.getMainImage())
                        .goodsSubtitle(good.getSubtitle())
                        .goodsPrice(good.getPrice())
                        .goodsStatus(good.getStatus())
                        .quantity(cartRedis.getQuantity())
                        .goodsStock(good.getStock())
                        .goodsSelected(cartRedis.getProducteSelected())
                        .goodsTotalPrice(good.getPrice().multiply(BigDecimal.valueOf(cartRedis.getQuantity()))).build();
                cartGoodsVos.add(cartGoodsVo);
                if (!cartRedis.getProducteSelected()){
                    selectAll = false;
                }
                // 计算总价，只计算选中的
                if (cartGoodsVo.getGoodsSelected()){
                    cartTotalPrice = cartTotalPrice.add(cartGoodsVo.getGoodsTotalPrice());
                }
            }
            cartTotalQuantity +=cartRedis.getQuantity();
        }
        cartVo.setCartGoodsVos(cartGoodsVos);
        cartVo.setSelectAll(selectAll);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setCartTotalPrice(cartTotalPrice);
        return cartVo;
    }

    @Override
    public CartVo updateCartGood(Integer uid, CartReq req) throws BusinessException {
        if (uid == null || req.getQuantity() <= 0 ){
            throw new BusinessException("uid 不能为空或者 quantity 小于0 ");
        }
        String redisKey = String.format(CART_REDIS_KEY, uid);
        HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
        Map<String, String> entries = opsForHash.entries(redisKey);
        logger.info("entity:{}",JSON.toJSONString(entries,true));
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            logger.info("keys:{}", entry.getKey());
            if (String.valueOf(req.getGoodId()).equals(entry.getKey())){
                String value = entry.getValue();
                CartRedis cartRedis = JSON.parseObject(value, CartRedis.class);
                cartRedis.setQuantity(req.getQuantity());
                cartRedis.setProducteSelected(req.getSelected());
                logger.info("entity{}",JSON.toJSONString(cartRedis,true));
                opsForHash.put(redisKey,String.valueOf(req.getGoodId()), JSON.toJSONString(cartRedis));
            }
        }
        CartVo cartVo = fetchCart(uid);
        return cartVo;
    }

    @Override
    public CartVo deleteCartVo(Integer uid, CartReq req) throws BusinessException {
        if (uid == null ){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        String redisKey = String.format(CART_REDIS_KEY,uid);
        HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
        opsForHash.delete(redisKey,String.valueOf(req.getGoodId()));
        CartVo cartVo = fetchCart(uid);
        return cartVo;
    }

    @Override
    public CartVo selectAll(Integer uid) throws BusinessException {
        if (uid == null){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        String redisKey = String.format(CART_REDIS_KEY,uid);
        HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
        List<CartRedis> cartRedis = listCartRedis(uid);
        for (CartRedis key : cartRedis) {
            key.setProducteSelected(true);
            opsForHash.put(redisKey,String.valueOf(key.getProductId()),JSON.toJSONString(key));
        }
        return fetchCart(uid);
    }

    @Override
    public CartVo notSelectAll(Integer uid) throws BusinessException {
        if (uid == null){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        String redisKey = String.format(CART_REDIS_KEY,uid);
        HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
        List<CartRedis> cartRedis = listCartRedis(uid);
        for (CartRedis key : cartRedis) {
            key.setProducteSelected(false);
            opsForHash.put(redisKey,String.valueOf(key.getProductId()),JSON.toJSONString(key));
        }
        return fetchCart(uid);
    }

    @Override
    public Integer sum(Integer uid) throws BusinessException {
        if (uid == null){
            throw new BusinessException(CommonCode.PARAMETER_ERROR);
        }
        Integer reduce = listCartRedis(uid).stream().map(CartRedis::getQuantity).reduce(0, Integer::sum);
        return reduce;
    }

    public List<CartRedis> listCartRedis(Integer uid){
        String redisKey = String.format(CART_REDIS_KEY,uid);
        List<CartRedis> cartRedisList = new ArrayList<>();
        HashOperations<String,String,String> opsForHash = redisTemplate.opsForHash();
        Map<String, String> entries = opsForHash.entries(redisKey);
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            cartRedisList.add(JSON.parseObject(entry.getValue(), CartRedis.class));
        }
        return cartRedisList;
    }
}
