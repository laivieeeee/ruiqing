package com.rq;

import com.ruiqing.RqItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/8/3 14:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= RqItemService.class)
public class RQTest {
   // @Autowired
   // RuiqingServiceImpl ruiqingService;

    @Test
    public void dd () {
        System.out.println("3333");
    }
    @Autowired
    RibbonLoadBalancerClient client;

    @Test
    public void test(){
        for (int i = 0; i < 100; i++) {
            ServiceInstance instance = this.client.choose("ims-service");
            System.out.println(instance.getHost() + ":" + instance.getPort());
        }
    }
}
