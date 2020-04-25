package com.biz.ironman.service;

import com.biz.ironman.dao.dataobject.Category;
import com.biz.ironman.vo.CategoryVo;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 13:15
 **/
public interface CategoryService {

    /**
     * 找到所有的父类目
     * @return
     */
    List<CategoryVo> fetchCategoryWithParent(Long id);
    /**
     * 发现所有的类目
     * @return
     */
    List<CategoryVo> fetchAllCategory();
}
