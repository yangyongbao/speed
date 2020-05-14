package org.speed.netty.server.netty.processor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import org.speed.netty.server.netty.EChannelRegister;
import org.speed.netty.server.netty.EConnection;
import org.speed.netty.server.netty.model.EMessage;
import org.speed.netty.server.netty.util.NetWorkUtil;
import org.speed.netty.server.netty.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class ESendProcessor {
	
	@Autowired
	EMoveProcessor eMoveProcessor;
	
	@PostConstruct
	public void init() {
		EChannelRegister register = EChannelRegister.getInstance();
		String serverIp = NetWorkUtil.localIp();
		
		int size = 1;
		Gson gson = new Gson();
		ExecutorService pools = Executors.newFixedThreadPool(size);
		for (int i = 0; i < size; i++) {
			
			Runnable task = new Runnable() {
				@Override
				public void run() {
					while (true) {
						
						try {
							
							
							String queueName = register.getServerIpQueueMap().get(serverIp);

							if(queueName != null ) {
								List<String> list = RedisUtils.jedis.brpop(0, queueName);
								if(list != null && list.size() > 0 ) {
									String content = list.get(1);
									EMessage message = gson.fromJson(content, EMessage.class);
									doSendMessage(register, message);
								}
							}
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			};
			
			pools.execute(task);
		}
	}
	
	private void doSendMessage(EChannelRegister register,EMessage message) {
		String target = message.deviceId();
		boolean findConnOnLocal = false;
		List<String> deviceIds = register.getGobleRouteMap().get(NetWorkUtil.localIp());
		for (String deviceId : deviceIds) {
			EConnection conn = register.getDeviceIdConnectionMap().get(deviceId);
			if(target.equals(deviceId)) {
				findConnOnLocal = true;
				
				conn.send(message.getContent(), new Runnable() {
					@Override
					public void run() {
						System.out.println("message=" + message.getContent() + " complete!!");
					}
				});
				
				break;
			}
		}
		
		if (!findConnOnLocal && !message.isExpire()) {
			eMoveProcessor.move(message);
		}
	}

}
