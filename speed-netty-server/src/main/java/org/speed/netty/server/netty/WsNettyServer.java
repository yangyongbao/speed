package org.speed.netty.server.netty;

import javax.annotation.PostConstruct;

import org.speed.netty.server.netty.handler.PushMessageHandler;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

@Component
public class WsNettyServer {
	
	int port = 8080;
	
	@PostConstruct
	public void start() {
		System.out.println("websocket server start...");

		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				EventLoopGroup bossGroup = new NioEventLoopGroup();
				EventLoopGroup workGroup = new NioEventLoopGroup();
				
				try {
					ServerBootstrap bootstrap = new ServerBootstrap();
					
					bootstrap.group(bossGroup, workGroup)
							 .option(ChannelOption.SO_BACKLOG, 1024)
							 .channel(NioServerSocketChannel.class)
							 //.localAddress(port)
							 .childHandler(new ChannelInitializer<SocketChannel>() {

								@Override
								protected void initChannel(SocketChannel channel) throws Exception {
									ChannelPipeline pipeline = channel.pipeline();
									
									pipeline.addLast(new HttpServerCodec());
									pipeline.addLast(new ChunkedWriteHandler());
									pipeline.addLast(new HttpObjectAggregator(8192));
									pipeline.addLast(new WebSocketServerProtocolHandler("/ws",null,true,65535));
									
									pipeline.addLast(new PushMessageHandler());
								}
								 
							 });
					
					ChannelFuture channelFuture = bootstrap.bind(port).sync();
		            channelFuture.channel().closeFuture().sync();
					System.out.println("websocket server end...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					System.out.println("websocket server shutdown...");

					bossGroup.shutdownGracefully();
					workGroup.shutdownGracefully();
				}
				
			}
			
		}).start();
		
		
	}
	
	public static void main(String[] args) {
		
		new WsNettyServer().start();
	}
	
	

}
