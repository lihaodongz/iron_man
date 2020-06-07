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

import javax.validation.Valid;
import java.awt.*;
import java.util.*;
import java.util.List;
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


    // 耗时 http(外网) > 磁盘 >内存
    // mysql(内网+磁盘)
    /**
     * 1. 查询所有
     * 2. 找出一级目录
     * 3. 查找一级目录子目录
     * @return
     */
    @Override
    public List<CategoryVo> fetchCategory(){
        List<Category> categories = categoryMapper.selectAll();
        // 找到所有的父节点
        List<CategoryVo> categoryVos = categories.stream()
                .filter(e -> e.getParentId().equals(0L))
                .map(this::convertCategoryVo)
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());
        findSubCategory(categoryVos,categories);
        return categoryVos;
    }

    @Override
    public void fetchSubCategory(Integer id, Set<Long> resultSet) throws BusinessException {
        List<Category> categories = categoryMapper.selectAll();
         fetchSubCategory(id,resultSet,categories);
    }

    public void fetchSubCategory(Integer id, Set<Long> resultSet,  List<Category> categories) throws BusinessException {
        for (Category category : categories) {
            if (category.getParentId().equals(id)){
                resultSet.add(category.getId());
                fetchSubCategory(category.getId(),resultSet);
            }
        }
    }
    public CategoryVo convertCategoryVo(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }


    public void findSubCategory(  List<CategoryVo> categoryVos, List<Category> categories){
        for (CategoryVo categoryVo : categoryVos) {
            List<CategoryVo> subCategoryvOList = new ArrayList<>();
            for (Category category : categories) {
                if (categoryVo.getId().equals(category.getParentId())){
                    CategoryVo categoryVo1 = convertCategoryVo(category);
                    subCategoryvOList.add(categoryVo1);
                }
            }
            subCategoryvOList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
            categoryVo.setSubCategory(subCategoryvOList);
            findSubCategory(subCategoryvOList,categories);
        }
    }
}
