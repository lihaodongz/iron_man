package com.biz.ironman.web.category;

import com.biz.ironman.exception.BusinessException;
import com.biz.ironman.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

/**
 * @author 李浩东
 * @description
 * @date 2020/4/25 12:49
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class categoryTest   {

    @Autowired
    CategoryService categoryService;

    @Test
    public void testfetchSubCategory() throws BusinessException {
        HashSet<Long> resultSet = new HashSet<>();
       categoryService.fetchSubCategory(100001l, resultSet);
       log.info("result:{}",resultSet);

    }
}
