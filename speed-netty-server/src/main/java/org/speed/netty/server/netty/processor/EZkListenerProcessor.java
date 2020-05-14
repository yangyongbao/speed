package org.speed.netty.server.netty.processor;

import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.speed.netty.server.netty.EChannelRegister;
import org.speed.netty.server.netty.util.ZKTools;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class EZkListenerProcessor {
	
	private PathChildrenCache childrenCache;
	
	@PostConstruct
	public void init() {
		
		String path = "/ws";
		watchChild(path);
		
		List<String> brokerList = ZKTools.childNodes(path);
		for (String broker : brokerList) {
			String subPath = path + "/" + broker ;
			watchChild(subPath);
			
			List<String> clientIdList = ZKTools.childNodes(subPath);
			EChannelRegister.getInstance().getGobleRouteMap().put(broker, clientIdList);
		}
	}
	
	protected void watchChild(String path) {
		try {
			EChannelRegister register = EChannelRegister.getInstance();
			
			childrenCache = new PathChildrenCache(ZKTools.client, "/ws", true);  
			childrenCache.start();
			
			childrenCache.getListenable().addListener(new PathChildrenCacheListener() {  
				@Override
				public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
					List<Type> types = Lists.newArrayList();
					types.add(Type.CHILD_ADDED);
					types.add(Type.CHILD_REMOVED);
					
					String broker = null,deviceId = null;
					if(types.contains(event.getType())) {
						ChildData data = event.getData();
						String[] arrays = data.getPath().split("\\/");
						
						if(arrays.length == 3 ) { //broker nodes 
							broker = arrays[2];
						}else if(arrays.length == 4 ) {//client nodes
							broker = arrays[2];
							deviceId = arrays[3];
						}
						
						updateRoute(event.getType(), broker, deviceId, register);
					}
				}
				
				void updateRoute(Type type,String broker,String deviceId,EChannelRegister register) {
					if(broker == null ) {
						return;
					}
					
					switch (type) {
						case CHILD_ADDED:
							
							if(Objects.isNull(deviceId)) {
								register.getGobleRouteMap().put(broker, ZKTools.childNodes("/ws/" + broker));
							}else {
								List<String> deviceIds = ZKTools.childNodes("/ws/" + broker);
								deviceIds.add(deviceId);
								register.getGobleRouteMap().put(broker, deviceIds);
							}
							
							break;
						case CHILD_REMOVED:
						
							if(Objects.isNull(deviceId)) {
								register.getGobleRouteMap().put(broker, ZKTools.childNodes("/ws/" + broker));
							}else {
								List<String> deviceIds = ZKTools.childNodes("/ws/" + broker);
								deviceIds.remove(deviceId);
								register.getGobleRouteMap().put(broker, deviceIds);
							}

							break;	
						default:
							
							break;
					}
					
					register.getServerIpQueueMap().clear();
					Set<Entry<String, List<String>>> entrySet = register.getGobleRouteMap().entrySet();
					for (Entry<String, List<String>> entry : entrySet) {
						String ip = entry.getKey();
						String path = "/ws" + entry.getKey();
						register.getServerIpQueueMap().put(ip, ZKTools.readNodeData(path));
					}
				}
	        });
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
