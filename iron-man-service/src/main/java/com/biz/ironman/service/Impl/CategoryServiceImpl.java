package com.biz.ironman.service.Impl;
import com.biz.ironman.dao.dataobject.Category;
import com.biz.ironman.dao.mapper.CategoryMapper;
import com.biz.ironman.service.CategoryService;
import com.biz.ironman.vo.CategoryVo;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
