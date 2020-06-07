package com.biz.ironman.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 13:26
 **/
@Data
public class CategoryVo {

    private Long id;
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Date createTime;
    private Date updateTime;
    private List<CategoryVo> subCategory;
}
