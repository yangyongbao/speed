package org.speed.netty.server.netty.handler;

import java.net.InetSocketAddress;

import org.speed.netty.server.netty.EChannelRegister;
import org.speed.netty.server.netty.EConnection;
import org.speed.netty.server.netty.model.EChannel;
import org.speed.netty.server.netty.util.NetWorkUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class PushMessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String serverIp = NetWorkUtil.localIp();
		String remote_id = remoteIp(ctx);
		Channel channel = ctx.channel();
		String deviceId = channel.id().asLongText().replaceAll("-", "");
		EConnection econn = EConnection.newEConnection(EChannel.newChannel(channel, deviceId),serverIp,remote_id);
		System.out.println("建立连接-channelId=" + channel.id() + ",deviceId=" + deviceId + ",remote_id=" + remote_id + ",serverIp=" + serverIp);
		EChannelRegister.getInstance().register(deviceId, econn);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		String serverIp = NetWorkUtil.localIp();
		Channel channel = ctx.channel();
		String deviceId = channel.id().asLongText().replaceAll("-", "");

		EChannel echannel = EChannel.newChannel(channel, deviceId);
		System.out.println("断开连接-channelId=" + channel.id() + ",deviceId=" + echannel.getDeviceId() + ",remote_id=" + remoteIp(ctx) + ",serverIp=" + serverIp );
		
		EChannelRegister.getInstance().unregister(deviceId);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		System.out.println("channelRead0=" + msg.text());
		
		ctx.channel().writeAndFlush(new TextWebSocketFrame("hello:" + System.currentTimeMillis()));
	}

	
	private String remoteIp(ChannelHandlerContext ctx) {
		InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
		String remote_ip = new String(socketAddress.getAddress().getHostAddress());
		return remote_ip;
	}
}
