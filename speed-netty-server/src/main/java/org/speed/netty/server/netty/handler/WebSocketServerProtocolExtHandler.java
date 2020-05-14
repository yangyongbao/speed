package org.speed.netty.server.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class WebSocketServerProtocolExtHandler extends WebSocketServerProtocolHandler {

	public WebSocketServerProtocolExtHandler(String websocketPath) {
		super(websocketPath);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		
		
		super.handlerAdded(ctx);
	}
	

}
