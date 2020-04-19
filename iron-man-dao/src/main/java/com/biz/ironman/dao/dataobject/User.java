package com.biz.ironman.dao.dataobject;

import lombok.Builder;
import lombok.Data;
import org.omg.CORBA.StringHolder;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.Date;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/18 16:49
 **/
@Data
@Table(name = "mall_user")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String question;
    private String answer;
    /**
     * 角色 0 管理员 1 普通用户
     */
    private Integer role;
    private Date createTime;
    private Date updateTime;
}
