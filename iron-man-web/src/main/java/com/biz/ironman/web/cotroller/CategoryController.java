package com.biz.ironman.web.cotroller;

import com.biz.ironman.common.CommonResponse;
import com.biz.ironman.common.ResultBuilder;
import com.biz.ironman.dao.dataobject.Category;
import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.service.CategoryService;
import com.biz.ironman.vo.CategoryVo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 13:28
 **/
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public CommonResponse<List<CategoryVo>> getcategory(){
      //  List<CategoryVo> categories =
            //    categoryService.fetchCategoryWithParent(0L);
        List<CategoryVo> categoryVos = categoryService.fetchCategory();
        return ResultBuilder.success(categoryVos);
    }

    @GetMapping("/categorys")
    public CommonResponse<List<CategoryVo>> getcategorys(){
        List<CategoryVo> categories =
                categoryService.fetchAllCategory();
        return ResultBuilder.success(categories);
    }

    @GetMapping("/getSubCategory")
    public CommonResponse<Set<Long>> getSubCategory(@RequestParam("categoryId") Integer categoryId) throws BusinessException {
        if (categoryId == null){
            categoryId = 0;
            // 查询所有结果
        }
        HashSet<Long> resultSet = new HashSet<>();
        //Long.valueOf() 会产生空指针异常
        Set<Long> longs = categoryService.fetchSubCategory(Long.valueOf(categoryId), resultSet);
        return ResultBuilder.success(longs);
    }
}
