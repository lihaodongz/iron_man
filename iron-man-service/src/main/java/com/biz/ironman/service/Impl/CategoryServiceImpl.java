package com.biz.ironman.service.Impl;
import com.biz.ironman.common.CommonCode;
import com.biz.ironman.dao.dataobject.Category;
import com.biz.ironman.dao.mapper.CategoryMapper;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.service.CategoryService;
import com.biz.ironman.vo.CategoryVo;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 13:16
 **/
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> fetchCategoryWithParent(Long id) {
        Category category = Category.builder().parentId(id).build();
        List<Category> parentCate = categoryMapper.select(category);
        List<CategoryVo> result = parentCate.stream().map(i -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(i, categoryVo);
            return categoryVo;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CategoryVo> fetchAllCategory() {
        List<CategoryVo> parentCategory = this.fetchCategoryWithParent(0L);
        parentCategory= getCategoryVos(parentCategory);
        return parentCategory;
    }

    @Override
    public Set<Long> fetchSubCategory(Long id,Set<Long> resultSet) throws BusinessException{
        List<Category> categories = categoryMapper.selectAll();
        fetchSubCategory(id,resultSet,categories);
        return resultSet;
    }
    private void  fetchSubCategory(Long id,Set<Long>resultSet,List<Category> categories) throws BusinessException {
        for (Category category : categories) {
            if (category.getParentId().equals(id)){
                resultSet.add(category.getId());
                fetchSubCategory(category.getId(),resultSet,categories);
            }
        }
    }

    public List<CategoryVo> getCategoryVos(List<CategoryVo> categoryVos){
        for (CategoryVo categoryVo : categoryVos) {
            if (categoryVo.getParentId() == null){
                continue;
            }
            List<CategoryVo> categoryVos1 = fetchCategoryWithParent(categoryVo.getId());
            categoryVo.setSubCategory(categoryVos1);
            getCategoryVos(categoryVos1);
        }
        return categoryVos;
    }
}
