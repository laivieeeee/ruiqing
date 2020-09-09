package com.rq;

import com.ruiqing.RqItemService;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.WatcherEvent;
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
    @Test
    public void testBgDecimal() throws Exception {
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 30000,new TestWatcher());
        String node = "/node2";
        Stat stat = zk.exists(node,false);
        if(stat == null){
            String s = zk.create(node, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(s);
        }
        byte[] b = zk.getData(node,false,stat);
        System.out.println("------++=============="+new String(b));
        zk.close();
    }
    class TestWatcher implements Watcher {

        @Override
        public void process(WatchedEvent watchedEvent) {
            String path = watchedEvent.getPath();
            Event.KeeperState state = watchedEvent.getState();
            Event.EventType type = watchedEvent.getType();
            WatcherEvent wrapper = watchedEvent.getWrapper();
            System.out.println(path);
            System.out.println("------------");
            System.out.println(state);
            System.out.println(type);
            System.out.println(wrapper);
        }
    }
}
