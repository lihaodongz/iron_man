package com.biz.ironman.dao.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.security.provider.certpath.PKIXTimestampParameters;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/19 11:05
 **/
@Table(name = "mall_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Date createTime;
    private Date updateTime;
}
