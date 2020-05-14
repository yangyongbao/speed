package org.speed.netty.server.netty.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.speed.netty.server.netty.model.EMessage;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

public class RedisUtils {
	public static Jedis jedis = new Jedis("localhost", 6379);
	
	
	public static void queueAdd(String queue,String message) {
		jedis.rpush(queue, message);
	}
	
	public static void main(String[] args) {
		
		/**
		jedis.setex("yyb01", 50, "1");
		
		String queueName = "127.0.0.1";
		
		while(true) {
			List<String> list = RedisUtils.jedis.brpop(0, queueName);
			
			System.out.println(list.toString());
		}**/
		
		EMessage eMessage = new EMessage("1",1,"1111");
		Map<String, String> ext = new HashMap<String, String>();
		ext.put("deviceId", "111");
		
		eMessage.setExt(ext );
		
		String string = new Gson().toJson(eMessage);
		
		System.out.println(string);
		
		
	}
}
