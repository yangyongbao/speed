package org.speed.kakfa.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class KafkaGroupConsumer { 

    @KafkaListener(topics = {"sync_order"},groupId = "push")
    public void receiveMessage(String message ) {
    	
    	System.out.println("message=" + message);
    }
}
