package com.rq;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.WatcherEvent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/8/3 14:52
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes= RqItemService.class)
public class RQTest {
   // @Autowired
   // RuiqingServiceImpl ruiqingService;

    @Test
    public void dd () {
        System.out.println("3333");
    }
    @Test
    public void dd1 () {
        // 1 获取文件系统
        /*Configuration configuration = new Configuration();
        // 配置在集群上运行
        // configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
        // FileSystem fs = FileSystem.get(configuration);

        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration, "atguigu");

        // 2 创建目录
        fs.mkdirs(new Path("/1108/daxian/banzhang"));

        // 3 关闭资源
        fs.close();*/
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
