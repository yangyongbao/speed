package org.speed.netty.server.netty.model;

import io.netty.channel.Channel;

public class EChannel {
	
	private String deviceId;
	
	private Channel channel;
	
	public static EChannel newChannel(Channel channel,String deviceId) {
		EChannel warp = new EChannel();
		warp.setChannel(channel);
		warp.setDeviceId(deviceId);
		
		return warp;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
