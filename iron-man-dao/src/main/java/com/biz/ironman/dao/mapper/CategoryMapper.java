package com.biz.ironman.dao.mapper;

import com.biz.ironman.dao.base.TkBaseMapper;
import com.biz.ironman.dao.dataobject.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 13:14
 **/
@Repository
public interface CategoryMapper  extends TkBaseMapper<Category> {


    /**
     * 按照父id查询
     * @param parentId
     * @return
     */
    @Select("select * form mall_category where parent_id = #{parent_id}")
    List<Category> selectByParentId(@Param("parent_id") Long parentId);


}
