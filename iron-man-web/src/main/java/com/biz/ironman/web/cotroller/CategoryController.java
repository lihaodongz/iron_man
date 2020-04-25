package com.biz.ironman.web.cotroller;

import com.biz.ironman.common.CommonResponse;
import com.biz.ironman.common.ResultBuilder;
import com.biz.ironman.dao.dataobject.Category;
import com.biz.ironman.service.CategoryService;
import com.biz.ironman.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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
        List<CategoryVo> categories =
                categoryService.fetchCategoryWithParent(0L);
        return ResultBuilder.success(categories);
    }

    @GetMapping("/categorys")
    public CommonResponse<List<CategoryVo>> getcategorys(){
        List<CategoryVo> categories =
                categoryService.fetchAllCategory();
        return ResultBuilder.success(categories);
    }
}
