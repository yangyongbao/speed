package org.speed.netty.server.netty;

import org.speed.netty.server.netty.model.EChannel;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class EConnection {

	private EChannel channel;
	
	private String serverIp;
	
	private String clientIp;
	
	public static EConnection newEConnection(EChannel channel, String serverIp,String clientIp) {
		return new EConnection(channel,serverIp,clientIp);
	}

	public EConnection(EChannel channel, String serverIp,String clientIp) {
		this.channel = channel;
		this.serverIp = serverIp;
		this.clientIp = clientIp;
	}
	
	public void send(String message,Runnable callback) {
		TextWebSocketFrame messagewarp = new TextWebSocketFrame(message);
		
		ChannelFuture cf = channel.getChannel().writeAndFlush(messagewarp);
		
		cf.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				try {
					callback.run();
				}catch (Exception e) {
				}
				
			}
		});
	}

	public EChannel getChannel() {
		return channel;
	}

	public void setChannel(EChannel channel) {
		this.channel = channel;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	
}
