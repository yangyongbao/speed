package org.speed.zookeeper;

import java.net.InetAddress;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
			
        	test1();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	System.out.println( "Hello World!" );
    }
    
    
    static void test1() throws Exception {
    	
    	CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryNTimes(10, 5000));
        client.start();// 连接
        
        
        client.create().forPath("/ws/10");

        
        List<String> dataList = client.getChildren().forPath("/ws");
        System.out.println(dataList);
        
        
        //String result = client.create().withMode(CreateMode.PERSISTENT).withACL(Ids.OPEN_ACL_UNSAFE).forPath("/ws/yyb02/1", "Data".getBytes());
        //System.out.println(result);
        
        /**
        List<String> children = client.getChildren().usingWatcher(new CuratorWatcher()
        {
            @Override
            public void process(WatchedEvent event) throws Exception
            {
                System.out.println("监控： " + event);
            }
        }).forPath("/");
        System.out.println("节点列表:" + children);**/
        
        
        
        //InetAddress localHost = InetAddress.getLocalHost();
        //String ip = localHost.getHostAddress();
        //System.out.println(ip);
       // String path = "/" + ip;
        
       
        // 创建节点
        //String result = client.create().withMode(CreateMode.PERSISTENT).withACL(Ids.OPEN_ACL_UNSAFE).forPath(path, "Data".getBytes());
        //System.out.println(result);
        
       // Stat stat = client.checkExists().forPath("/127.0.0.1");
        
        //System.out.println("===" + stat);
        
        // 设置节点数据
        //client.setData().forPath("/ws/yyb01", "999".getBytes());
        
        // 删除节点
        //System.out.println(client.checkExists().forPath("/test"));
        //client.delete().forPath("/ws/yyb02");
        //System.out.println(client.checkExists().forPath("/test"));
        
        client.close();
        System.out.println("OK！");
    	
    }
}
