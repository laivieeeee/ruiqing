package com.rq;

import com.ruiqing.RqItemService;
import com.ruiqing.service.impl.RuiqingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/8/3 14:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= RqItemService.class)
public class RQTest {
    @Autowired
    RuiqingServiceImpl ruiqingService;

    @Test
    public void dd () {
        System.out.println("3333");
    }
}
