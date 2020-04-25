package com.biz.ironman.dao.mapper;

import com.biz.ironman.dao.base.TkBaseMapper;
import com.biz.ironman.dao.dataobject.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import sun.awt.image.IntegerComponentRaster;

import java.util.List;
import java.util.Set;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 13:14
 **/
@Repository
public interface CategoryMapper  extends TkBaseMapper<Category> {


    @Select("select * form mall_category where parent_id = #{parent_id}")
    List<Category> selectByParentId(@Param("parent_id") Integer parentId);


}
