package org.speed.netty.server.netty.util;

import java.util.Date;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import com.google.common.collect.Lists;

public class ZKTools {

	private static String zkServerUrl = "127.0.0.1:2181";
	
	public static CuratorFramework client;
	
	static {
		client = CuratorFrameworkFactory.newClient(zkServerUrl, new RetryNTimes(10, 5000));
        client.start();	
	}
	
	public static void createNode(String path,String subPath,String subPathVal) {
		try {
			//broker 节点
			if(client.checkExists().forPath(path) == null ) {
				String value = "ws_mq_queue_" + NetWorkUtil.localIp() + "_" + new Date().toGMTString();
				client.create().withMode(CreateMode.EPHEMERAL)
					  .forPath(path, value.getBytes());
			}
			
			//client 节点
			client.create().withMode(CreateMode.EPHEMERAL)
						   .forPath(path + "/" + subPath, subPathVal.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String readNodeData(String path) {
		try {
			Stat stat = new Stat();
			byte[] nodeData = client.getData().storingStatIn(stat).forPath(path);
			return new String(nodeData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void deleteNode(String path) {
		try {
			client.delete().forPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkNodeExist(String path) {
		try {
			Stat ex = client.checkExists().forPath(path);
			if(ex == null ) {
				return false;
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static List<String> childNodes(String path) {
		List<String> childNodes = Lists.newArrayList();
		try {
			childNodes = client.getChildren().forPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return childNodes;
	}
	
	public static void close() {
		client.close();
	}
	
	
}
