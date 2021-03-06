package com.biz.ironman.service;

import com.biz.ironman.dao.dataobject.Category;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.vo.CategoryVo;

import java.util.List;
import java.util.Set;

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


    Set<Long> fetchSubCategory(Long id, Set<Long> resultSet) throws BusinessException;

     List<CategoryVo> fetchCategory();


     void fetchSubCategory(Integer id,Set<Long> resultSet) throws BusinessException;
}
