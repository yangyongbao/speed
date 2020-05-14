package org.speed.netty.server.netty.processor;

import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.speed.netty.server.netty.EChannelRegister;
import org.speed.netty.server.netty.model.EMessage;
import org.speed.netty.server.netty.util.NetWorkUtil;
import org.speed.netty.server.netty.util.RedisUtils;
import org.speed.netty.server.netty.util.ZKTools;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

@Component
public class EMoveProcessor {
	
	@PostConstruct
	void init() {
		ScheduledExecutorService pools = Executors.newScheduledThreadPool(1);
		pools.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				List<EMessage> eMessages = Lists.newArrayList();
				for (EMessage eMessage : eMessages) {
					move(eMessage);
				}
			}
		}, 5, 5, TimeUnit.SECONDS);
	}

	public void move(EMessage message) {
		EChannelRegister register = EChannelRegister.getInstance();
		String deviceId = message.deviceId();
		
		String queueName = null;
		Set<Entry<String, List<String>>> entrySet = register.getGobleRouteMap().entrySet();
		for(Entry<String, List<String>> entry : entrySet ) {
			if(entry.getValue().contains(deviceId)) {
				queueName = register.getServerIpQueueMap().get(entry.getKey());
				break;
			}
		}
		
		String content = new Gson().toJson(message);
		if(Objects.nonNull(queueName)) {
			RedisUtils.queueAdd(queueName, content);
		}else {
			RedisUtils.queueAdd("ws_push_backup", content);
		}
	}

	public void moveAll(String queueName) {
		//检测队列所在的主键是否已经重启完成
		List<EMessage> eMessages = Lists.newArrayList();
		for (EMessage eMessage : eMessages) {
			
			boolean nodeExist = ZKTools.checkNodeExist( "/ws/" + NetWorkUtil.localIp());
			if(nodeExist) {
				break;
			}
			
			if(!eMessage.isExpire()) {
				move(eMessage);
			}
		}
	}
}
