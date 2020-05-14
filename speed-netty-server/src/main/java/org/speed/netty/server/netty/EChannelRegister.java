package org.speed.netty.server.netty;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.speed.netty.server.netty.util.ZKTools;


public class EChannelRegister {
	
	static EChannelRegister INSTANCE = new EChannelRegister();
	
	public static EChannelRegister getInstance(){
		return INSTANCE;
	}
	
	/*Local Connected DeviceId Map */
	private volatile Map<String/*deviceId*/,EConnection> deviceIdConnectionMap = new ConcurrentHashMap<>();
	
	/*Goable Connection RouteMap By Listener Zookeeper Sync Update */
	private volatile Map<String/*serverIp*/,List<String>/*clientIds*/> gobleRouteMap = new ConcurrentHashMap<>();

	private volatile Map<String/*backupQueue*/,String/*queueName*/> gobleBackupQueueMap = new ConcurrentHashMap<>();
	
	private volatile Map<String/*serverIp*/,String/*queueName*/> serverIpQueueMap = new ConcurrentHashMap<>();
	
	public void register(String deviceId,EConnection connection) {
		String queueName = connection.getServerIp();
		
		deviceIdConnectionMap.put(deviceId, connection);
		serverIpQueueMap.put(connection.getServerIp(), queueName);
		
		String path = "/ws/" + connection.getServerIp() ;
		String subPath = connection.getChannel().getDeviceId();
		String subPathVal = connection.getClientIp();
		ZKTools.createNode(path, subPath,subPathVal);
	}
	
	public void unregister(String deviceId) {
		EConnection connection = deviceIdConnectionMap.get(deviceId);
		
		deviceIdConnectionMap.remove(deviceId);
		serverIpQueueMap.remove(connection.getServerIp());
		
		String path = "/ws/" + connection.getServerIp() + "/"  + deviceId;
		ZKTools.deleteNode(path);
	}

	public Map<String, EConnection> getDeviceIdConnectionMap() {
		return deviceIdConnectionMap;
	}

	public void setDeviceIdConnectionMap(Map<String, EConnection> deviceIdConnectionMap) {
		this.deviceIdConnectionMap = deviceIdConnectionMap;
	}

	public Map<String, String> getServerIpQueueMap() {
		return serverIpQueueMap;
	}

	public void setServerIpQueueMap(Map<String, String> serverIpQueueMap) {
		this.serverIpQueueMap = serverIpQueueMap;
	}

	public Map<String, List<String>> getGobleRouteMap() {
		return gobleRouteMap;
	}

	public void setGobleRouteMap(Map<String, List<String>> gobleRouteMap) {
		this.gobleRouteMap = gobleRouteMap;
	}
	
	
	
	
	
	
}

