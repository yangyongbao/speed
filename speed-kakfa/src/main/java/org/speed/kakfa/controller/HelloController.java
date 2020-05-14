package org.speed.kakfa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

	@RequestMapping("/send")
	public String send() {
		
		String data = "data:" + System.currentTimeMillis();
		String topic = "sync_order";
		
		ListenableFuture<SendResult<String, String>> rs = kafkaTemplate.send(topic , data);
		rs.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			
			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("Send on Success: " + result.toString());
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Send on onFailure: the Topic=" + topic + ",data=" + data + ",exception=" + ex);
			}
			
		});
		
		return "ok";
	}
	

}
